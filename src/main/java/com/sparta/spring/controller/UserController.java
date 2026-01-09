package com.sparta.spring.controller;

import com.sparta.spring.dto.UserRequestDto;
import com.sparta.spring.dto.UserResponseDto;
import com.sparta.spring.service.UserService;
import jakarta.validation.Valid; // 추가
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users") // 경로를 /api/users로 공통화
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "유저 삭제 완료 (ID: " + id + ")";
    }
}