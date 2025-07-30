package net.daum.service;

import java.util.List;

import net.daum.dao.AdminGongjiDAO;
import net.daum.dao.AdminGongjiDAOImpl;
import net.daum.dto.BoardDTO;
import net.daum.dto.GongjiDTO;

public class AdminGongjiServiceImpl implements AdminGongjiService {
	
	/* 관리자 공지사항 ServiceImpl */

	private AdminGongjiDAO adminGongjiDao = AdminGongjiDAOImpl.getInstance();

	@Override
	public int getListCount(GongjiDTO findB) {
		return this.adminGongjiDao.getTotalCount(findB);
	}//관리자 공지사항 검색전 총 레코드 개수, 검색 후 레코드 개수

	@Override
	public List<BoardDTO> getGongjiList(GongjiDTO findB) {
		return this.adminGongjiDao.getGongjiList(findB);
	}//관리자 공지사항 검색전 총목록, 검색 후 목록

	@Override
	public void adminGongjiInsert(GongjiDTO g) {
		this.adminGongjiDao.adminGongjiInsert(g);
	}//관리자 공지저장

	@Override
	public GongjiDTO getAdminGongjiCont(int gongji_no) {
		return this.adminGongjiDao.getAdminGongjiCont(gongji_no);
	}//관리자 공지사항 상세정보 보기

	@Override
	public void editAdminGongji(GongjiDTO eg) {
		this.adminGongjiDao.editAdminGongji(eg);
	}//관리자 공지사항 수정

	@Override
	public void deleteAdminGongji(int gongji_no) {
		this.adminGongjiDao.deleteAdminGongji(gongji_no);
	}//관리자 공지사항 삭제
}
