package org.example.board.dto;

import lombok.Getter;

@Getter
public class BoradWithAgeResposeDto {

    private final String title;

    private final String contents;

    private final Integer age;

    public BoradWithAgeResposeDto(String title, String contents, Integer age) {
        this.title = title;
        this.contents = contents;
        this.age = age;
    }
}
