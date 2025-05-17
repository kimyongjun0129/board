package org.example.borad.service;

import lombok.RequiredArgsConstructor;
import org.example.borad.dto.BoardResponseDto;
import org.example.borad.entity.Board;
import org.example.borad.entity.Member;
import org.example.borad.repository.BoardRepository;
import org.example.borad.repository.MemberRepository;
import org.springframework.stereotype.Service;

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
}
