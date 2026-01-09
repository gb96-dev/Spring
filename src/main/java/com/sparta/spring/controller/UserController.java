package com.sparta.spring.controller;

import com.sparta.spring.dto.LoginRequestDto;
import com.sparta.spring.dto.UserRequestDto;
import com.sparta.spring.dto.UserResponseDto;
import com.sparta.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup") // 회원가입 경로를 명확히 분리
    public UserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    // [Lv 4] 로그인 API 추가
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        UserResponseDto userResponseDto = userService.login(loginRequestDto);

        // 세션 생성 및 유저 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("userId", userResponseDto.getId());

        return "로그인 성공! 환영합니다 " + userResponseDto.getUsername() + "님";
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}