package net.daum.dao;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.AdminDTO;
import net.daum.mybatis.config.DBService;

public class AdminDAOImpl implements AdminDAO {
	
	/*
	 * 관리자 정보 저장과 로그인 인증 DAOImpl
	 */

	// 정적 필드
	private static AdminDAOImpl instance = null;

	public AdminDAOImpl() {} //생성자	

	// DAOImpl 객체 생성해서 반환
	public static AdminDAOImpl getInstance() {
		if(instance == null) {
			instance = new AdminDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlSession을 반환

	@Override
	public void insertAdmin(AdminDTO ab) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();
			
			sqlSession.insert("admin_in", ab);//mybatis에서 insert()메서드는 레코드 저장, admin_in은 admin.xml에서 설정할 유일한 insert 아이디명
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//insertAdmin() -> 관리자 정보 저장

	@Override
	public AdminDTO adminLogin(String admin_id) {
		SqlSession sqlSession = null;

		try {			
			sqlSession = getSqlSession();
			return sqlSession.selectOne("admin_login", admin_id);							
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//adminLoign() -> 관리자 로그인 인증
}
