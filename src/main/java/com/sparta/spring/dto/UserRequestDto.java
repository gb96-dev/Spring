package com.sparta.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이어야 합니다.") // Lv 3 핵심 조건
    private String password;
}