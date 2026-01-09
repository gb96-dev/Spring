package com.sparta.spring.repository;

import com.sparta.spring.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 기본 CRUD 메서드가 자동으로 생성됩니다.
}