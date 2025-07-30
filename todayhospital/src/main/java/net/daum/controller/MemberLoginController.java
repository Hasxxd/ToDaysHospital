package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* 서블릿 MVC 로그인 전 뷰페이지 화면 과 로그인 인증 후 뷰페이지 화면을 볼수 있는 컨트롤러 클래스, 그리고 비번찾기 공지창 */
public class MemberLoginController implements Action {

	/*  서비스 흐름은 컨트롤러 ↔ 서비스 ↔ DAO ↔ MyBatis 매퍼태그 해당 아이디를 호출해서 쿼리문 수행
	 *  처음 만들때 파일 생성과 실제 코드 작업은 서비스 흐름의 반대로 하는 것이 좋다.
	 *  그 다음 부터는 서비스 흐름대로 daum.properties 파일에 /매핑주소=execute|풀패키지경로
	 *  (예시:  /member_login.do=execute|net.daum.controller.MemberLoginController) 생성한 다음 
	 *  -> 컨틀롤러 -> 서비스 -> DAO -> MyBatis  순번대로 작업하면 된다.
	 *  
	 *  패키지 정리)
	 *   net.daum.controller -> 자바 웹애플리케이션 프로그램 개발에서 매핑주소가 시작될때 실행되는 컨트롤러 클래스가 있는 곳이다. 이 
	 *   컨트롤러 클래스는 Action.java 인터페이스를 구현상속 받아서 execute();추상메서드를 오버라이딩 한다. 이 오버라이딩 된 메서드의 
	 *   반환 타입은 ActionForward이다.
	 *   net.daum.service -> 컨트롤러와 DAO사이 중간 매개체 역할을 하는 *Service 가 들어가는 곳
	 *   net.daum.dao ->  DAO (Data Access Object) 실제로 데이터베이스(DB)에 접근하여 CRUD(Create(데이터 저장) -> insert 쿼리문 ,
	 *    Read(데이터 조회) -> select 쿼리문 , Update, Delete) 작업을 수행하는 곳 
	 *   net.daum.mappers.mybatis -> sql문을 담고 있는 mybatis 매퍼태그 파일이 위치한 곳   
	 *   
	 *   net.daum.dto -> 람복 라이브러리를 사용한 데이터 저장빈 클래스가 들어간 곳
	 *   net.daum.mybatis.config -> DTO빈클래스 객체 별칭이름, 트랜잭션 설정과 커넥션 풀, 데이터베이스 연결 정보와 SQL문을 담고 있는 
	 *   MyBatis 매퍼태그 파일 경로가 설정된 sqlmap.xml 파일이 있는 곳. 그리고 이 파일을 읽어와 mybatis 쿼리문 실행 객체 SqlSession을 
	 *   생성하는 팩토리가 있는 DBService.java 가 있는곳이면서 이 파일에서 정적메서드 getFactory()를 호출하면 정적 팩토리 반환한다.
	 */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    ActionForward forward = new ActionForward();
	    forward.setPath("/WEB-INF/view/total_login/total_Login/login.jsp");
	    forward.setRedirect(false);
	    return forward;
	}
}
