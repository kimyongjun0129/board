package org.example.borad.service;

import lombok.RequiredArgsConstructor;
import org.example.borad.dto.MemberResponseDto;
import org.example.borad.dto.SignUpResponseDto;
import org.example.borad.entity.Member;
import org.example.borad.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

        Optional<Member> member = memberRepository.findById(id);

        if(member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Does not exists id : " + id);
        }

        Member findMember = member.get();

        return new MemberResponseDto(findMember.getUsername(), findMember.getAge());
    }
}
