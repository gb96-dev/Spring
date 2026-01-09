package com.sparta.spring.service;

import com.sparta.spring.dto.ScheduleRequestDto;
import com.sparta.spring.dto.ScheduleResponseDto;
import com.sparta.spring.entity.Schedule;
import com.sparta.spring.entity.User;
import com.sparta.spring.repository.ScheduleRepository;
import com.sparta.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository; // 유저 조회를 위해 추가

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // 1. userId로 유저가 존재하는지 확인
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID: " + requestDto.getUserId()));

        // 2. 유저 객체를 포함하여 일정 생성
        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContents(), user);

        // 3. DB 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );
        schedule.update(requestDto.getTitle(), requestDto.getContents());
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public Long deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );
        scheduleRepository.delete(schedule);
        return id;
    }
}