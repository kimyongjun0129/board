package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.dto.MemberResponseDto;
import org.example.board.dto.SignUpResponseDto;
import org.example.board.entity.Member;
import org.example.board.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, Integer age) {

        Member member = new Member(username, password, age);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getAge())
;    }

    public MemberResponseDto findById(Long id) {

        Member member = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(member.getUsername(), member.getAge());
    }

    @Transactional
    public void updatePasssword(Long id, String oldPassword, String newPassword) {

        Member member = memberRepository.findByIdOrElseThrow(id);

        if(!member.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        member.updatePassword(newPassword);
    }
}
