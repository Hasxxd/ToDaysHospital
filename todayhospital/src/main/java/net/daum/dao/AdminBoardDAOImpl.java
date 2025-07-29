package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.BoardDTO;
import net.daum.mybatis.config.DBService;

public class AdminBoardDAOImpl implements AdminBoardDAO {
	
	/* 관리자 게시판 DAOImpl */

	// 정적 필드
	private static AdminBoardDAOImpl instance = null;

	public AdminBoardDAOImpl() {} //생성자	

	// DAOImpl 객체 생성에서 반환
	public static AdminBoardDAOImpl getInstance() {
		if(instance == null) {
			instance = new AdminBoardDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlsession 반환

	@Override
	public int getTotalCount(BoardDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectOne("ab_count",findB);   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. ab_count는 admin_board.xml에서 설정할 유일한 select 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}		
	}//검색 전 총 레코드 개수, 검색 후 레코드 개수

	@Override
	public List<BoardDTO> getBoardList(BoardDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();//mybatis 쿼리문을 수행할 sqlSession 반환
			return sqlSession.selectList("ab_list", findB);
			//mybatis에서 selectList()메서드는 복수개의 레코드를 검색해서 컬렉션 List로 반환. ab_list는 admin_board.xml에서 설정할 유일한 select 아이디명
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//검색전 총목록과 검색후 목록

	@Override
	public void adminBoardInsert(BoardDTO board) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();

			sqlSession.insert("ab_in", board);//mybatis에서 insert()메서드는 레코드 저장, ab_in은 admin_board.xml에서 설정할 유일한 insert 아이디명
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//관리자 게시판 저장

	@Override
	public BoardDTO getAdminBoardCont(int board_no) {
		SqlSession sqlSession = null;

		try{
			sqlSession = getSqlSession();
			return sqlSession.selectOne("ab_cont", board_no);
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//관리자 게시판 내용보기

	@Override
	public void editAdminBoard(BoardDTO eb) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.update("ab_edit", eb);//mybatis에서 update()메서드는 레코드를 수정한다.
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//관리자 게시판 수정

	@Override
	public void delAdminBoard(int board_no) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.delete("ab_del", board_no);//mybatis 에서 delete() 메서드는 레코드를 삭제, ab_del은 admin_board.xml에서 설정할 유일한 delete
		    //아이디명
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//관리자 게시판 삭제
}
