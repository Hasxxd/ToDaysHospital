package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.MemberDTO;
import net.daum.mybatis.config.DBService;

public class AdminMemberDAOImpl implements AdminMemberDAO {

	/* 관리자 회원관리 DAOImpl */

	// 정적 필드
	private static AdminMemberDAOImpl instance = null;

	public AdminMemberDAOImpl() {} //생성자	

	//DAOImpl 객체 생성에서 반환
	public static AdminMemberDAOImpl getInstance() {
		if(instance == null) {
			instance = new AdminMemberDAOImpl();
		}
		return instance;
	}//getInstance()

	//sqlSession을 매번 새로 생성하여 반환
	private SqlSession getSqlSession() {		
		return DBService.getFactory().openSession(false);//false -> 수동 commit 모드
	}//getSqlSession() => mybatis  쿼리문 수행할 sqlsession 반환

	@Override
	public int getMemberCount(MemberDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectOne("am_count",findB);   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. am_count는 mybatis 매퍼태그에서 설정할 유일한 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}
	}//getMemberCount() -> 검색전 총회원수 또는 검색후 회원수

	@Override
	public List<MemberDTO> getMemberList(MemberDTO findB) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();//mybatis 쿼리문을 수행할 sqlSession 반환
			return sqlSession.selectList("am_list", findB);
			//mybatis에서 selectList()메서드는 복수개의 레코드를 검색해서 컬렉션 List로 반환. am_list는 mybatis 매퍼태그에서 설정할 유일한 아이디명
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//getMemberList() -> 검색전 총회원목록 또는 검색후 회원목록

	@Override
	public MemberDTO getAdminMemberInfo(String mem_id) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();
			return sqlSession.selectOne("am_info",mem_id);   
			//mybatis에서 selectOne() 메서드는 단 한개의 레코드만 반환. am_info는 mybatis 매퍼태그에서 설정할 유일한 아이디명	
		}finally {
			if(sqlSession != null) {
				sqlSession.close();//항상 세션 종료
			}
		}
	}//getAdminMemberInfo() -> 관리자 회원 상세정보 보기

	@Override
	public void editMember(MemberDTO m) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.update("am_edit", m);//mybatis에서 update()메서드는 레코드를 수정한다.am_edit는 mybatis 매퍼태그에서 설정할 유일한 아이디명
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//editMember() -> 관리자 회원 정보수정

	@Override
	public void delMem(String mem_id) {
		SqlSession sqlSession = null;

		try {
			sqlSession = getSqlSession();

		    sqlSession.update("am_del", mem_id);
		    //mybatis에서 delete()메서드는 레코드를 삭제한다.am_del은 admin_member.xml에서 설정할 유일한 delete 아이디명
		    sqlSession.commit();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}//관리자에서 회원삭제

}
