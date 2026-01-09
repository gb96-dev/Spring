## 📊 ERD (Entity Relationship Diagram)

<img width="400" height="400" alt="image" src="https://github.com/user-attachments/assets/2394f538-51b8-40ca-b199-170bbb274f13" />


---

📑 API 명세서기능MethodURL인증RequestResponse상태 코드회원가입POST/api/users/signupXBody (JSON)등록된 유저 정보201 Created로그인POST/api/users/loginXBody (JSON)성공 메시지 및 세션200 OK유저 목록 조회GET/api/usersX-유저 리스트200 OK일정 생성POST/api/schedulesOBody (JSON)생성된 일정 정보201 Created일정 전체 조회GET/api/schedulesX-전체 일정 리스트200 OK일정 단건 조회GET/api/schedules/{id}XPath Variable선택 일정 정보200 OK일정 수정PUT/api/schedules/{id}OBody (JSON)수정된 일정 정보200 OK일정 삭제DELETE/api/schedules/{id}OPath Variable-200 OK
