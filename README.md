# 🏥 오늘의 병원 (병원 예약 및 진료 일정 관리 시스템)

## 📌 개요

**‘오늘의 병원’**은 병원 예약, 전자 문진, 실시간 진료 관리 등 병원 이용 전반을 디지털화하는 시스템입니다.  
환자의 편의성과 병원의 운영 효율을 동시에 개선하는 것을 목표로 하며,  
**위치 기반 병원 검색**, **전자 문진 자동 제출**, **예약 승인/이력 관리**, **로그 기반 통계** 등을 제공합니다.

## 🎯 주요 기능

### 👤 사용자(환자)
- 진료과 및 위치 기반 병원 검색 (지도 API 연동 예정)
- 실시간 예약 등록 / 조회
- 전자 문진서 작성 및 자동 제출
- 로그인 기능 (ID/PW, OAuth2 예정)

### 🏥 병원 관리자
- 병원 기본 정보 및 진료 일정 관리
- 예약 요청 승인 / 거절
- 문진 내역 열람 및 진료 참고

### 🔐 시스템 관리자
- 사용자/병원 계정 및 권한 관리 (계정 잠금/활성화 포함)
- 예약 로그 및 통계 조회 (예: 예약 건수, 진료과별 추이)
- 인증 정책, 로그인 실패 횟수 제한 등 설정 관리

## 🛠 기술 스택

| 구분       | 사용 기술 |
|------------|-----------|
| **Backend** | Java 17, Jakarta EE (Servlet 6.0), Maven, MyBatis, Tomcat 11 |
| **Frontend** | JSP, JSTL, HTML5, CSS3, JavaScript |
| **Database** | Oracle 21c XE (XEPDB1), JDBC, mybatis-config 기반 SQL 매핑 |
| **보안/인증** | OAuth 2.0 (예정: Naver, Google, Kakao), CAPTCHA (예정) |
| **API 연동** | 공공데이터포털 API, Naver Maps API (예정) |
| **개발 환경** | VSCode, GitHub, SVN, OneDrive, Windows 10 |
| **형상 관리** | GitHub (소스 코드) + SVN (문서), 병렬 백업 체계 |

## 📁 프로젝트 구조

\`\`\`
middleProject/
├── src/
│   ├── main/
│   │   ├── java/com/todayhospital/
│   │   │   ├── controller/        # 요청 분기 및 JSP 이동
│   │   │   ├── service/           # 비즈니스 로직 (로그인, 예약 처리 등)
│   │   │   ├── dao/               # DAO + MyBatis 연동 (XML 기반)
│   │   │   ├── dto/               # DTO (CamelCase 필드, lombok 적용)
│   │   │   └── utils/api/naver/   # 외부 API 연동 예정 공간
│   │   └── resources/mappers/     # MyBatis 매퍼 XML (예약, 환자, 시스템 로그 등)
├── webapp/
│   └── WEB-INF/views/             # JSP 화면 (로그인, 메인, 예약 등)
├── database/
│   ├── schema.sql                 # 테이블/제약조건 정의
│   └── seed.sql                   # 초기 더미 데이터 삽입용
└── pom.xml                        # Maven 의존성 관리 (JSTL, ojdbc, Lombok 등)
\`\`\`

## 🚀 실행 방법

### ▶️ 실행 주소:
http://localhost:8080/login_ok.do

### ✅ 사전 준비
- JDK 17, Tomcat 11 설치
- Oracle XE 또는 XEPDB1 사용자 계정(local_test) 생성 및 권한 부여
- application.properties 또는 .env에 DB 접속 정보 분리 구성
- Maven 빌드: `mvn clean package`
- WAR 배포: VSCode + Tomcat 연동 또는 수동 복사 (`target/*.war` → `Tomcat/webapps`)

## 📈 현재 구현 완료 상태 (2025.07 기준)

| 기능 항목             | 구현 여부 | 비고 |
|----------------------|-----------|------|
| 로그인 / 로그아웃    | ✅        | PatientMapper.xml + 세션 처리 완료 |
| 로그인 실패 횟수 제한 | ✅        | 5회 초과 시 계정 잠금 |
| 예약 기능             | ⏳        | Mapper 구조 분리 완료, 등록 기능 구현 중 |
| 전자 문진             | ⏳        | DTO/DB 구조 정의 완료, JSP 연동 미완 |
| 병원 검색 (위치 기반) | ⏳        | Naver Map API 연동 예정 |
| 관리자 권한 분리      | ⏳        | Role 필드 정의 예정 (회원가입 연계 예정) |
| 통계 시각화           | ⏳        | 추후 JSP + Plotly.js 방식으로 계획 |

## 🧭 향후 계획

- [ ] 회원가입 기능 → 관리자/환자 구분 구조 설계
- [ ] 진료과 및 병원 정보 정규화 → 예약 필터 기능 강화
- [ ] Naver API 기반 병원 위치 검색 기능
- [ ] JSP 기반 사용자 통계 시각화 (Plotly.js)
- [ ] 관리자용 대시보드 및 계정 관리 화면 추가

## 🤝 협업 전략

- GitHub: 소스 코드 및 커밋 주석 엄격 관리
- SVN + OneDrive: 산출물 / 설계서 / 회의록 버전 관리
- QA 프로세스: 오전반/오후반 통합 QA 테스트 진행 중
- 커밋 시 주요 변경 사항, 영향 범위 명시 필수
- 로컬 환경 문제 발생 시 스크립트 기반 Tomcat 설정 자동화