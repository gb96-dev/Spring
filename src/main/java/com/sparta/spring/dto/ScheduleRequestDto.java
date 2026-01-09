package com.sparta.spring.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private Long userId; // 이름 대신 유저 ID를 받음
}