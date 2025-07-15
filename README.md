# 오늘의 병원 ((병원 예약 및 진료 일정 관리 시스템))

## 개요
본 시스템은 환자의 병원 이용 절차를 간소화하고, 병원 관리자의 운영 효율을 높이기 위해 개발된 예약 및 문진 관리 플랫폼입니다. 위치 기반 병원 검색, 전자문진, 예약 및 결제 기능을 하나의 흐름으로 통합 제공합니다.

## 주요 기능

### 사용자(환자)
- 병원 검색 (진료과, 위치 기반 필터)
- 실시간 예약 등록 및 확인
- 전자 문진서 작성
- 네이버 소셜 로그인

### 병원 관리자
- 병원 및 의료진 정보 관리
- 예약 요청 확인 및 승인/거절
- 문진 내역 열람

### 시스템 관리자
- 회원 및 병원 계정 관리
- 서비스 통계, 예약 로그 분석
- 인증 및 보안 설정

## 기술 스택

| 구분 | 내용 |
|------|------|
| Backend | Java 17, Jakarta EE, Spring 일부 |
| Frontend | HTML, CSS, JavaScript, Naver Maps API |
| Database | Oracle (원격 DB) |
| 인증 | OAuth2.0 (Naver), reCAPTCHA |
| API | 공공데이터포털, 지도 API, 결제 API 예정 |
| 도구 | VSCode, Maven, Tomcat 11, GitHub, SVN 예정 |

## 프로젝트 구조

```
hospital-reservation-system/
├── backend/
│   ├── src/main/java/
│   │   ├── controller/
│   │   ├── dao/
│   │   ├── dto/
│   │   └── service/
│   └── resources/
├── frontend/
│   ├── static/
│   └── templates/
├── database/
│   ├── schema.sql
│   └── seed.sql
├── README.md
└── pom.xml
```

## 실행 방법

```bash
$ git clone https://github.com/[팀명]/hospital-reservation-system.git
$ cd hospital-reservation-system
$ mvn clean install
$ mvn tomcat7:run
```

- Java 17 필요
- Oracle 원격 DB 접속 설정 필요 (IP 및 계정 별도 안내)
- OAuth2.0 클라이언트 키 환경변수 또는 설정파일로 주입

## 산출 문서

- `수행계획서.pdf`
- `요구사항정의서.pdf`
- `유스케이스 다이어그램.drawio`
- `ERD 논리 및 물리.erd`
- `테이블정의서.xlsx`
- `화면정의서.pdf`
- `API 명세서.pdf`
- `시연계획.pptx`

## 향후 계획

- 전자 문진 기능 DB 연동 보완
- 결제 API 연동 및 테스트
- 사용자 통계 시각화 기능 추가
- 역할 기반 권한 관리 강화

## 협업 및 운영 전략

- 개발 브랜치 기준 GitHub 관리
- 산출물은 SVN 및 One Drive, 카카오톡 연계 백업
- 오전/오후반 프로젝트 환경 통합 검토 중
