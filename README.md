# 오늘의 병원 (병원 예약 및 진료 일정 관리 시스템)

## 📌 개요
본 시스템은 병원 예약 및 문진 과정을 자동화하여 환자의 병원 이용 절차를 간소화하고, 
병원 및 관리자 측의 운영 효율을 높이는 것을 목표로 함.  
위치 기반 병원 검색, 전자 문진, 예약/관리, 통계 등 의료 서비스 흐름을 통합적으로 제공.

## 🎯 주요 기능

### 사용자(환자)
- 진료과 및 위치 기반 병원 검색
- 실시간 예약 등록 및 조회
- 전자 문진서 작성 (진료 전 제출)
- (네이버, 카카오, 구글 등) 소셜 로그인 (OAuth 2.0)

### 병원 관리자
- 병원 정보 및 의료진 관리
- 예약 요청 확인 및 승인/거절 처리
- 사용자 문진 내역 열람 및 참고

### 시스템 관리자
- 사용자 및 병원 계정 권한 관리
- 예약 로그 및 사용 통계 분석
- 인증, 보안 정책 및 설정 관리

## 🛠 기술 스택

| 항목 | 구성 |
|------|------|
| Backend | Java 17, Jakarta EE, Maven, MyBatis, Tomcat 11 |
| Frontend | JSP, HTML5, CSS3, JavaScript |
| Database | Oracle 21c (Remote - XE / XEPDB1 기반) |
| 인증 및 보안 | OAuth2.0 (Naver, 카카오, 구글 등), reCAPTCHA v2 |
| 외부 API | 공공데이터포털 API, Naver Maps API |
| 개발 도구 | VSCode, GitHub, Draw.io, OneDrive |

## 🧱 프로젝트 구조

```
middleProject/
├── src/
│   ├── main/
│   │   ├── java/com/todayhospital/
│   │   │   ├── controller/        # 요청 분기 및 페이지 이동
│   │   │   ├── service/           # 비즈니스 로직
│   │   │   ├── dao/               # DB 접근 (MyBatis 인터페이스)
│   │   │   ├── dto/               # 데이터 전송 객체
│   │   │   └── utils/api/naver/   # 외부 API 연동
│   │   └── resources/mappers/     # MyBatis XML 매퍼
├── webapp/
│   └── WEB-INF/views/             # JSP 화면
├── database/
│   ├── schema.sql                 # 테이블 및 제약조건 정의
│   └── seed.sql                   # 초기 더미 데이터
└── pom.xml                         # (VSCode + maven) 라이브러리 
```

## 🚀 실행 방법

> ✅ **사전 준비**
> - JDK 17 설치 및 환경변수 설정
> - Oracle XE (또는 XEPDB1) 접속 정보 필요 (별도 외부 환경변수로 격리)
> - `application.properties` 또는 `.env`에 API Key 및 DB 접속 정보 구성
> - (로컬 테스트 시) `local_test` 사용자 생성 및 권한 부여 필요

## 🧭 향후 계획
- [ ] 로그인 / 로그아웃 기능
- [ ] 회원가입 기능
- [ ] 전자 문진 기능 DB 연동 완료 및 진료과 기반 필터링
- [ ] 사용자 통계 시각화 기능 (예: Plotly + JSP)
- [ ] 병원/관리자 권한 분리 강화 (Role 기반 접근제어)

## 🤝 협업 및 운영 전략
- GitHub, 주요 산출물은 (SVN,Redmine(로컬))/OneDrive/카카오톡 병행 백업
- SVN은 산출문서 버전 관리용으로 보조 사용 예정
- 오전반/오후반 통합 테스트 및 QA 대응 체계 마련 중
- 주요 커밋에는 구현 기능 및 테스트 여부 명시

---
