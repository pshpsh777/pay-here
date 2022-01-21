# pay-here
- Java Spring-boot
- mysql 5.7

# 데이터 모델링
![image](https://user-images.githubusercontent.com/7835902/150500747-8cca658b-ec84-4f8f-beee-4246528eb1d4.png)

# 기능 명세
### 회원가입
_이메일, 패스워드, 이름_
### 로그인
_이메일, 패스워드_
### 로그아웃
### 가계부 관리
1. 지출 등록 (금액, 메모)
2. 지출 내용 수정 (금액, 메모)
3. 지출 내용 삭제
4. 삭제 내용 복구
5. 지출리스트 조회
6. 지출 상세내용 조회

# API 명세
## 회원가입
Request 
```
POST /api/join
{
  "email": "pay@here.com",
  "password": "1234"
}
```

## 로그인
Request 
```
POST /api/login
{
  "email": "pay@here.com",
  "password": "1234"
}
```
## 로그아웃
Request 
```
GET /api/logout
HEADER
Authorization
```
## 가계부관리
### 지출 등록
Request
```
POST /api/expenditure
{
  "amounts": 10,000
  "memo": "dinner"
}
```
### 지출내용 수정
Request 
```
PUT /api/expenditure/{id}
{
  "amounts": 9,000
  "memo": "dinner"
}
```
### 지출 내용 삭제
Request 
```
POST /api/expenditure/delete/{id}
```
### 삭제 내용 복구
Request 
```
POST /api/expenditure/recover/{id}
```
### 지출리스트 조회
Request 
```
GET /api/expenditure
```
### 지출 상세내용 조회
Request 
```
GET /api/expenditure/{id}
```
### 삭제된 지출리스트 조회
Request 
```
GET /api/expenditure/deleted
```
