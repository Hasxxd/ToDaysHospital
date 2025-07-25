@echo off
REM === 환경 변수 확인 ===
if not defined VS_CODE_TOMCAT_HOME (
    echo [ERROR] 환경 변수 VS_CODE_TOMCAT_HOME이 정의되지 않았습니다.
    echo 예: set VS_CODE_TOMCAT_HOME=C:\Tomcat
    exit /b 1
)

set WAR_NAME=todayhospital.war
set TARGET_WAR=target\%WAR_NAME%
set DEPLOY_DIR=%VS_CODE_TOMCAT_HOME%\webapps

echo [INFO] Maven 빌드 시작...
call mvn clean package

if not exist %TARGET_WAR% (
    echo [ERROR] .war 파일 생성 실패: %TARGET_WAR%
    exit /b 1
)

echo [INFO] 기존 배포본 제거 중...
del /Q "%DEPLOY_DIR%\%WAR_NAME%" >nul 2>&1
rmdir /S /Q "%DEPLOY_DIR%\todayhospital" >nul 2>&1

echo [INFO] .war 파일 배포 디렉토리로 복사 중...
copy /Y %TARGET_WAR% "%DEPLOY_DIR%\"

echo [INFO] Tomcat 서버 중지 중...
call "%VS_CODE_TOMCAT_HOME%\bin\shutdown.bat"

timeout /t 2 >nul

echo [INFO] Tomcat 서버 시작 중...
call "%VS_CODE_TOMCAT_HOME%\bin\startup.bat"

echo [SUCCESS] 배포 완료 :
echo http://localhost:8080/todayhospital/login.do
echo http://localhost:8080/todayhospital/index.do