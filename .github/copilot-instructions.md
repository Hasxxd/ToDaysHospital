# Copilot Instructions for 오늘의 병원 (ToDaysHospital)

## 프로젝트 개요 및 구조
- **오늘의 병원**은 Java 17, Jakarta EE(Servlet 6.0), MyBatis, Oracle DB, JSP 기반의 병원 예약/진료 관리 시스템입니다.
- 주요 구조:
  - `controller/`: 모든 .do 요청을 받아 분기(Front Controller 패턴, 예: `MFrontController.java`)
  - `service/`: 비즈니스 로직(로그인, 예약 등)
  - `dao/`, `mappers/`: DB 연동(MyBatis, XML 매퍼)
  - `dto/`: 데이터 전달 객체(CamelCase, Lombok 적용)
  - `webapp/WEB-INF/views/`: JSP 화면
  - `resources/`: 설정 및 매퍼 XML, DB 연결 정보(`application.properties`)

## 핵심 개발/배포 워크플로우
- **빌드:**
  - Maven 사용: `mvn clean package` → `target/todayhospital.war` 생성
  - Tomcat 11에 WAR 배포(자동/수동)
- **DB:**
  - Oracle XE/XEPDB1, 계정(local_test) 필요
  - DB 접속 정보는 `application.properties`에서 관리
- **실행:**
  - 예시: `http://localhost:8080/todayhospital/login.do`
- **프론트 컨트롤러:**
  - 모든 요청은 `MFrontController`에서 처리, properties 기반 매핑(`application.properties`)
  - 예: `/login.do=execute|com.middleproject.controller.LoginAction`

## 주요 패턴 및 규칙
- **Action/ActionForward 패턴:**
  - 각 요청은 Action 구현체에서 처리, 결과는 ActionForward로 이동 방식(redirect/forward) 결정
- **MyBatis 매퍼:**
  - SQL은 XML 매퍼(`resources/mappers/`)에 정의, DAO에서 호출
- **DTO:**
  - CamelCase 필드, Lombok 사용 권장
- **로그인/인증:**
  - 로그인 실패 5회 시 계정 잠금(비즈니스 로직/DB 처리)
- **커밋/협업:**
  - GitHub 소스, SVN/OneDrive 문서 병행 관리
  - 커밋 메시지에 영향 범위 명시

## 외부 연동 및 확장
- **API:**
  - Naver Maps, 공공데이터포털 API 연동 예정
- **보안:**
  - OAuth2, CAPTCHA 등 확장 예정

## 참고 파일
- `README.md`: 전체 구조, 빌드/실행법, 구현 현황
- `application.properties`: DB/매핑 설정
- `MFrontController.java`: 요청 분기/실행 핵심
- `pom.xml`: 의존성 관리

---

**이 문서는 AI 에이전트가 본 프로젝트에서 빠르게 생산성을 낼 수 있도록, 실제 코드/구조/관행에 기반해 작성되었습니다.**
