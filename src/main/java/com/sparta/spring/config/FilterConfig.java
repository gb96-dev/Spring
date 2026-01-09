package com.sparta.spring.config;

import com.sparta.spring.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // 필터 등록
        filterRegistrationBean.setOrder(1); // 제일 먼저 실행되도록 순서 지정
        filterRegistrationBean.addUrlPatterns("/*"); // 모든 URL에 적용

        return filterRegistrationBean;
    }
}