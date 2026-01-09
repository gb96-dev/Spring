package com.sparta.spring.service;

import com.sparta.spring.dto.UserRequestDto;
import com.sparta.spring.dto.UserResponseDto;
import com.sparta.spring.entity.User;
import com.sparta.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        user.update(requestDto.getUsername(), requestDto.getEmail());
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        userRepository.delete(user);
    }
}

