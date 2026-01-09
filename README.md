## 📊 ERD (Entity Relationship Diagram)

<img width="400" height="400" alt="image" src="https://github.com/user-attachments/assets/2394f538-51b8-40ca-b199-170bbb274f13" />


---
## 📑 API 명세서

| 기능 | Method | URL | 인증 | Request | Response | 상태 코드 |
| :--- | :---: | :--- | :---: | :--- | :--- | :---: |
| 회원가입 | `POST` | `/api/users/signup` | X | Body (JSON) | 등록된 유저 정보 | 201 Created |
| 로그인 | `POST` | `/api/users/login` | X | Body (JSON) | 성공 메시지 및 세션 | 200 OK |
| 유저 목록 조회 | `GET` | `/api/users` | X | - | 유저 리스트 | 200 OK |
| 일정 생성 | `POST` | `/api/schedules` | O | Body (JSON) | 생성된 일정 정보 | 201 Created |
| 일정 전체 조회 | `GET` | `/api/schedules` | X | - | 전체 일정 리스트 | 200 OK |
| 일정 단건 조회 | `GET` | `/api/schedules/{id}` | X | Path Variable | 선택 일정 정보 | 200 OK |
| 일정 수정 | `PUT` | `/api/schedules/{id}` | O | Body (JSON) | 수정된 일정 정보 | 200 OK |
| 일정 삭제 | `DELETE` | `/api/schedules/{id}` | O | Path Variable | - | 200 OK |
