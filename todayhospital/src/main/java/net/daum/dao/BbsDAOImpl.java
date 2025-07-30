package net.daum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;
import net.daum.mybatis.config.DBService;

public class BbsDAOImpl implements BbsDAO {
	
	/*  사용자 자료실 DAOImpl	 */

	// 정적 필드
	private static BbsDAOImpl instance = null;

	public BbsDAOImpl() {} //생성자	

	// DAOImpl 객체 생성해서 반환
	public static BbsDAOImpl getInstance() {
		if(instance == null) {
			instance = new BbsDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlsession 반환

	@Override
	public int seqNumberNext() {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			
			return sqlSession.selectOne("bbsNoSeq_Find");   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. bbsNoSeq_Find는 mybatis 매퍼xml태그에서 설정할 유일한 아이디명	
			
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}		
	}//newbbs_no_seq.NEXTVAL 다음 시퀀스 번호값 반환 	

	@Override
	public void bbsInsert(BbsDTO bbs) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();

			sqlSession.insert("bbs_in", bbs);//mybatis에서 insert()메서드는 레코드 저장, bbs_in은 mybatis 매퍼xml태그에서 설정할 유일한 아이디명
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//tbl_newbbs 테이블에 자료실 저장

	@Override
	public void updateAttached_file(int attachedNumber, int bbs_no) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();

			Map<String,Object> pm=new HashMap<>();	
			pm.put("attachedNumber",attachedNumber);//첨부파일이 있는 경우 7
			pm.put("bbs_no",bbs_no);//기준이 될 자료실 번호
						
			sqlSession.update("bbs_attached_file", pm);//mybatis에서 update()메서드는 레코드 수정,
			//bbs_attached_file는 mybatis 매퍼xml태그에서 설정할 유일한 아이디명
			
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//첨부파일이 있는 경우만 bbs_attached_file 컬럼 레코드를 7로 수정=> 7이 있다면 첨부파일이 있는 경우이고 null이면 없다는 의미

	@Override
	public void insertFile(FileDTO fileDto) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)	
			sqlSession = getSqlSession();

			sqlSession.insert("file_info", fileDto);//mybatis에서 insert()메서드는 레코드 저장, file_info은 mybatis 매퍼xml태그에서 설정할 유일한 아이디명
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//한개 또는 다중파일 업로드한 파일정보 저장

	@Override
	public int getRowCount(BbsDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectOne("bbs_count", findB);   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. bbs_count는 mybatis 매퍼xml태그에서 설정할 유일한 아이디명
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}	
	}//검색 전 총 레코드 개수/검색후 레코드 개수

	@Override
	public List<BbsDTO> getBbsList(BbsDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectList("bbs_list", findB);   
			//mybatis에서 selectList() 메서드는  하나이상의 레코드를 검색해서 컬렉션 List로 반환. bbs_list는 mybatis 매퍼xml태그에서 설정할 유일한 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}	
	}//검색 전 총 페이징 목록 /검색후 페이징 목록

	@Override
	public BbsDTO getBbsCont(int bbs_no) {
		SqlSession sqlSession = null;

		try{
			sqlSession = getSqlSession();
			
			sqlSession.update("bbs_hi", bbs_no);//조회수 증가
			sqlSession.commit();

			return sqlSession.selectOne("bbs_co", bbs_no);			
		}catch(Exception e) {
			if(sqlSession != null) {
				sqlSession.rollback();//예외 발생하면 롤백처리
			}
			e.printStackTrace();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
		
		return null;
	}//getBbsCont() -> 조회수 증가와 내용보기 함께 트랜잭션 처리

	@Override
	public BbsDTO getBbsCont2(int bbs_no) {
		SqlSession sqlSession = null;

		try{
			sqlSession = getSqlSession();
			return sqlSession.selectOne("bbs_co", bbs_no);
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//getBbsCont2() -> 수정폼,삭제폼,답변폼 => 조회수 증가는 안됨.

	@Override
	public List<FileDTO> getFileInfo(int bbs_no) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectList("file_list", bbs_no);   
			//mybatis에서 selectList() 메서드는  하나이상의 레코드를 검색해서 컬렉션 List로 반환. file_list는 mybatis 매퍼xml태그에서 설정할 유일한 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}	
	}//자료실 번호를 기준으로 첨부파일 정보를 읽어온다

	@Override
	public void bbsReply(BbsDTO bbsdata) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

			sqlSession.update("levelUp", bbsdata);//답변 레벨 증가
			sqlSession.insert("reply_in2", bbsdata);//답변 저장
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
	}//bbsReply() -> 답변레벨 증가와 답변저장 함께 트랜잭션 처리

	@Override
	public void editBbs(BbsDTO bbs) {
		/* 서블릿 MVC에서는 기본적으로 트랜잭션 관리가 Spring과 같은 프레임워크에서 자동으로 관리되는 방식과 다릅니다. 
		 * 서블릿 MVC에서는 트랜잭션을 수동으로 처리 
		 */
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)		
			sqlSession = getSqlSession();			
						
			sqlSession.update("bbs_edit", bbs);//mybatis에서 update()메서드는 레코드 수정, bbs_edit는 mybatis 매퍼태그에서 설정할 유일한 아이디명
			
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//번호를 기준으로 글쓴이,글제목,글내용만 수정

	@Override
	public void delFileList(int bbs_no) {
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)		
			sqlSession = getSqlSession();			
						
			sqlSession.delete("bbs_file_del", bbs_no);//mybatis에서 delete()메서드는 레코드 삭제, bbs_file_del은 mybatis 매퍼태그에서 
			//설정할 유일한 아이디명
			
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//번호를 기준으로 기존 첨부파일 목록 정보를 해당 테이블인 tbl_newbbs_file로 부터 삭제

	@Override
	public void bbsDelete(int bbs_no) {
		SqlSession sqlSession = null;

		try {
			//sqlSession 시작(트랜잭션 비자동 커밋)		
			sqlSession = getSqlSession();			
						
			sqlSession.delete("bbs_del", bbs_no);//mybatis에서 delete()메서드는 레코드 삭제, bbs_del은 mybatis 매퍼태그에서 
			//설정할 유일한 아이디명
			
			sqlSession.commit();
			/* SqlSession의 경우 auto commit이 비활성화되어 있기 때문에 반드시 DML 실행 후 commit()을 명시적으로 실행해야 한다는 것이다.  커밋은
			 * 수동으로 처리
			 */		
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//세션 종료
			}
		}
	}//번호를 기준으로 tbl_newbbs 테이블로 부터 자료 삭제
}
