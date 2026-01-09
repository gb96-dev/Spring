package com.sparta.spring.repository;

import com.sparta.spring.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

      List<Schedule> findAllByOrderByModifiedAtDesc();

}