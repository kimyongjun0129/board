package org.example.borad.controller;

import lombok.RequiredArgsConstructor;
import org.example.borad.dto.MemberResponseDto;
import org.example.borad.dto.SignUpRequestDto;
import org.example.borad.dto.SignUpResponseDto;
import org.example.borad.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> singUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getAge()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {

        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }
}
