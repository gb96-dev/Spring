package com.sparta.spring.service;

import com.sparta.spring.dto.LoginRequestDto;
import com.sparta.spring.dto.UserRequestDto;
import com.sparta.spring.dto.UserResponseDto;
import com.sparta.spring.entity.User;
import com.sparta.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        // 비밀번호 8자 이상 검증
        if (requestDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    // [Lv 4] 로그인 로직 추가
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        // 1. 이메일로 유저 조회
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 2. 비밀번호 일치 여부 확인
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        return new UserResponseDto(user);
    }
}