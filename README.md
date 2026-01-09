## 📊 ERD (Entity Relationship Diagram)

![ERD]<img width="400" height="400" alt="image" src="https://github.com/user-attachments/assets/2394f538-51b8-40ca-b199-170bbb274f13" />


---

## 📑 API 명세서

| 기능 | Method | URL | 인증 필요 |
| :--- | :---: | :--- | :---: |
| 회원가입 | POST | `/api/users/signup` | X |
| 로그인 | POST | `/api/users/login` | X |
| 일정 생성 | POST | `/api/schedules` | O |
| 일정 수정 | PUT | `/api/schedules/{id}` | O |
| 일정 삭제 | DELETE | `/api/schedules/{id}` | O |
