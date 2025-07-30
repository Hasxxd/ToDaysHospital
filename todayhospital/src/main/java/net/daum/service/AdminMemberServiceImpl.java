package net.daum.service;

import java.util.List;

import net.daum.dao.AdminMemberDAO;
import net.daum.dao.AdminMemberDAOImpl;
import net.daum.dto.MemberDTO;

public class AdminMemberServiceImpl implements AdminMemberService {

	/* 관리자 회원관리 ServiceImpl */
	 
	private AdminMemberDAO adminMemberDao = AdminMemberDAOImpl.getInstance();

	@Override
	public int getMemberCount(MemberDTO findB) {
		return this.adminMemberDao.getMemberCount(findB);
	}//검색전 총회원수 또는 검색후 회원수	

	@Override
	public List<MemberDTO> getMemberList(MemberDTO findB) {
		return this.adminMemberDao.getMemberList(findB);
	}//검색전 총회원목록 또는 검색후 회원목록

	@Override
	public MemberDTO getAdminMemberInfo(String mem_id) {
		return this.adminMemberDao.getAdminMemberInfo(mem_id);
	}//관리자 회원 상세정보 보기

	@Override
	public void editMember(MemberDTO m) {
		this.adminMemberDao.editMember(m);
	}//관리자에서 회원정보 수정

	@Override
	public void delMember(String mem_id) {
		this.adminMemberDao.delMem(mem_id);
	}//관리자에서 회원삭제
}
