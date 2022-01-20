# pay-here
- Java Spring-boot
- mysql 5.7

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
POST /api/join
{
  "email": "pay@here.com",
  "password": "1234"
}

## 로그인
POST /api/login
{
  "email": "pay@here.com",
  "password": "1234"
}

## 로그아웃
POST /api/logout
{
  "email": "pay@here.com"
}

## 가계부관리
### 지출 등록
POST /api/account
{
  "amounts": 10,000
  "memo": "dinner"
}
### 지출내용 수정
PUT /api/account/{id}
{
  "amounts": 9,000
  "memo": "dinner"
}
### 지출 내용 삭제
POST /api/account/delete/{id}

### 삭제 내용 복구
POST /api/account/recover/{id}

### 지출리스트 조회
GET /api/account

### 지출 상세내용 조회
GET /api/account/{id}

# 데이터 모델링
## user
![image](https://user-images.githubusercontent.com/7835902/149606119-c17e723c-6131-4154-8469-765d397d5d79.png)
