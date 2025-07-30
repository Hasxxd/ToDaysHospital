package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.GongjiDTO;
import net.daum.mybatis.config.DBService;

public class GongjiDAOImpl implements GongjiDAO {
	
	/* 사용자 공지사항 DAOImpl */

	// 정적 필드
	private static GongjiDAOImpl instance = null;

	public GongjiDAOImpl() {} //생성자	

	//DAOImpl 객체 생성에서 반환
	public static GongjiDAOImpl getInstance() {
		if(instance == null) {
			instance = new GongjiDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlsession 반환

	//사용자 공지목록
	@Override
	public List<GongjiDTO> getGongjiList() {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();//mybatis 쿼리문을 수행할 sqlSession 반환
			return sqlSession.selectList("g_list");
			//mybatis에서 selectList()메서드는 복수개의 레코드를 검색해서 컬렉션 List로 반환. g_list는 gongji.xml에서 설정할 유일한 select 아이디명
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//getGongjiList()

	//사용자 공지내용 조회수 증가와 내용보기 => 트랜잭션 처리
	@Override
	public GongjiDTO getGongCont(int gongji_no) {
		SqlSession sqlSession = null;

		try{
			sqlSession = getSqlSession();
			
			sqlSession.update("g_hit", gongji_no);//조회수 증가
			sqlSession.commit();

			return sqlSession.selectOne("g_cont", gongji_no);			
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
	}//getGongCont()
}
