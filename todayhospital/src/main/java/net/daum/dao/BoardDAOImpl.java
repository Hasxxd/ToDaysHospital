package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.BoardDTO;
import net.daum.mybatis.config.DBService;

public class BoardDAOImpl implements BoardDAO {
	
	/* 사용자 게시판 DAOImpl */

	// 정적 필드
	private static BoardDAOImpl instance = null;

	public BoardDAOImpl() {} //생성자	

	// DAOImpl 객체 생성에서 반환
	public static BoardDAOImpl getInstance() {
		if(instance == null) {
			instance = new BoardDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlsession 반환

	@Override
	public int getListCount(BoardDTO findB) {				
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectOne("board_row",findB);   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. board_row는 board.xml에서 설정할 유일한 select 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}
	}//검색전후 총레코드 개수, 검색후 레코드 개수

	@Override
	public List<BoardDTO> getBoardList(BoardDTO findB) {		
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();//mybatis 쿼리문을 수행할 sqlSession 반환
			return sqlSession.selectList("board_li", findB);
			//mybatis에서 selectList()메서드는 복수개의 레코드를 검색해서 컬렉션 List로 반환. board_li는 board.xml에서 설정할 유일한 select 아이디명
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//검색 전 총 목록, 검색 후 목록

	@Override
	public void insertBoard(BoardDTO board) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();

			sqlSession.insert("board_in", board);//mybatis에서 insert()메서드는 레코드 저장, board_in은 board.xml에서 설정할 유일한 insert 아이디명
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//게시판 저장

	@Override
	public BoardDTO getBoardCont(int board_no) {
		SqlSession sqlSession = null;

		try{
			sqlSession = getSqlSession();
			return sqlSession.selectOne("board_co", board_no);
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//번호에 해당하는 게시판 내용보기

	@Override
	public void updateHit(int board_no) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			sqlSession.update("board_hi", board_no);//mybatis에서 update()메서드는 레코드를 수정
			sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//내용보기일때만 조회수 증가

	//답변레벨 증가와 답변저장 함께 트랜잭션 처리
	@Override
	public void boardReply(BoardDTO boarddata) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

			sqlSession.update("reply_up", boarddata);//답변 레벨 증가
			sqlSession.insert("reply_in", boarddata);//답변 저장
			sqlSession.commit();
		}catch(Exception e) {
			if(sqlSession != null) {
				sqlSession.rollback();//예외 발생하면 롤백처리
			}
			e.printStackTrace();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//boardReply()

	//번호를 기준으로 제목,글쓴이,내용을 수정
	@Override
	public void editBoard(BoardDTO eb) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.update("board_up", eb);
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//editBoard()

	//번호를 기준으로 삭제
	@Override
	public void deleteBoard(int board_no) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.delete("board_del", board_no);//mybatis에서 delete()메서드는 레코드를 삭제
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//deleteBoard()
}
