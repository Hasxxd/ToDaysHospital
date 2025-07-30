package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.BoardDTO;
import net.daum.dto.GongjiDTO;
import net.daum.mybatis.config.DBService;

public class AdminGongjiDAOImpl implements AdminGongjiDAO {

	/* 관리자 공지사항 DAOImpl */
	
	// 정적 필드
	private static AdminGongjiDAOImpl instance = null;

	public AdminGongjiDAOImpl() {} //생성자	

	// DAOImpl 객체 생성에서 반환
	public static AdminGongjiDAOImpl getInstance() {
		if(instance == null) {
			instance = new AdminGongjiDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlsession 반환

	//관리자 공지사항 검색전 총 레코드 개수, 검색후 레코드 개수
	@Override
	public int getTotalCount(GongjiDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectOne("ag_count",findB);   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. ag_count는 admin_gongji.xml에서 설정할 유일한 select 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}
	}//getTotalCount()

	//관리자 공지사항 검색전 총목록, 검색후 목록
	@Override
	public List<BoardDTO> getGongjiList(GongjiDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();//mybatis 쿼리문을 수행할 sqlSession 반환
			return sqlSession.selectList("ag_list", findB);
			//mybatis에서 selectList()메서드는 복수개의 레코드를 검색해서 컬렉션 List로 반환. ag_list는 admin_gongji.xml에서 설정할 유일한 select 아이디명
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//getGongjiList()

	@Override
	public void adminGongjiInsert(GongjiDTO g) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();

			sqlSession.insert("ag_in", g);//mybatis에서 insert()메서드는 레코드 저장, ag_in은 admin_gongji.xml에서 설정할 유일한 insert 아이디명
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//관리자 공지저장

	@Override
	public GongjiDTO getAdminGongjiCont(int gongji_no) {
		SqlSession sqlSession = null;

		try{
			sqlSession = getSqlSession();
			return sqlSession.selectOne("ag_cont", gongji_no);
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//관리자 공지사항 상세정보 보기

	@Override
	public void editAdminGongji(GongjiDTO eg) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.update("ag_edit", eg);//mybatis에서 update()메서드는 레코드를 수정한다.
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//관리자 공지사항 수정

	@Override
	public void deleteAdminGongji(int gongji_no) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.delete("ag_del", gongji_no);//mybatis 에서 delete() 메서드는 레코드를 삭제, ag_del은 admin_gongji.xml에서
		    //설정할 유일한 delete 아이디명
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//관리자 공지사항 삭제
}
