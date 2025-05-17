package org.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.board.dto.BoardResponseDto;
import org.example.board.dto.BoradWithAgeResposeDto;
import org.example.board.dto.CreateBoardRequestDto;
import org.example.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto) {

        BoardResponseDto boardResponseDto = boardService.save(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getContents()
        );
        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {
        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoradWithAgeResposeDto> findById(@PathVariable Long id) {

        BoradWithAgeResposeDto boradWithAgeResposeDto = boardService.findById(id);

        return new ResponseEntity<>(boradWithAgeResposeDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        boardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
