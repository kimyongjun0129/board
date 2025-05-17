package org.example.borad.service;

import lombok.RequiredArgsConstructor;
import org.example.borad.dto.BoardResponseDto;
import org.example.borad.dto.BoradWithAgeResposeDto;
import org.example.borad.entity.Board;
import org.example.borad.entity.Member;
import org.example.borad.repository.BoardRepository;
import org.example.borad.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto save(String username, String title, String contents) {
        Member member = memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title, contents);
        board.setMember(member);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents());
    }

    public List<BoardResponseDto> findAll() {;
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::toDto)
                .toList();
    }

    public BoradWithAgeResposeDto findById(Long id) {
        Board board = boardRepository.findByIdOrElseThrow(id);
        Member member = board.getMember();

        return new BoradWithAgeResposeDto(board.getTitle(), board.getContents(), member.getAge());
    }

    public void delete(Long id) {
        Board borad = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(borad);
    }
}
