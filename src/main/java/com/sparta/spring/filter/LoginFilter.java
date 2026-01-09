package com.sparta.spring.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    // 인증을 거치지 않아도 되는 화이트리스트 (로그인, 회원가입, 전체조회 등)
    // 이 경로들은 로그인하지 않은 상태에서도 접근이 가능합니다.
    private static final String[] WHITE_LIST = {"/api/users/signup", "/api/users/login", "/api/users", "/api/schedules"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            // 1. 화이트리스트에 포함되지 않은 경로인지 확인 (수정, 삭제 등 인증이 필요한 경로)
            if (!isWhiteList(requestURI)) {

                // 2. 세션을 가져옴 (세션이 없으면 새로 생성하지 않고 null 반환)
                HttpSession session = httpRequest.getSession(false);

                // 3. 세션이 없거나, 세션에 로그인 시 저장했던 "userId"가 없는 경우
                if (session == null || session.getAttribute("userId") == null) {

                    // 401 Unauthorized 상태 코드 설정
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    // 한글 깨짐 방지를 위한 인코딩 설정
                    httpResponse.setContentType("text/plain;charset=UTF-8");
                    httpResponse.setCharacterEncoding("UTF-8");

                    // 에러 메시지 출력 후 종료
                    httpResponse.getWriter().write("로그인이 필요합니다.");
                    return; // 여기서 return을 해야 컨트롤러로 요청이 넘어가지 않습니다.
                }
            }

            // 4. 인증이 되었거나 화이트리스트 경로라면 다음 필터 또는 컨트롤러로 이동
            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e; // 예외 발생 시 상위로 던짐
        }
    }

    /**
     * 화이트리스트 여부 확인 메서드
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}