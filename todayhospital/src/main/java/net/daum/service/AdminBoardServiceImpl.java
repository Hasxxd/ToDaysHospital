package net.daum.service;

import java.util.List;

import net.daum.dao.AdminBoardDAO;
import net.daum.dao.AdminBoardDAOImpl;
import net.daum.dto.BoardDTO;

public class AdminBoardServiceImpl implements AdminBoardService {

	/* 관리자 게시판 서비스Impl */
	
	private AdminBoardDAO adminBoardDao = AdminBoardDAOImpl.getInstance();

	@Override
	public int getListCount(BoardDTO findB) {
		return this.adminBoardDao.getTotalCount(findB);
	}//검색전 총레코드 개수, 검색후 레코드 개수	

	@Override
	public List<BoardDTO> getBoardList(BoardDTO findB) {
		return this.adminBoardDao.getBoardList(findB);
	}//검색전 총 목록, 검색 후 목록

	@Override
	public void boardInsert(BoardDTO boarddata) {
		this.adminBoardDao.adminBoardInsert(boarddata);
	}//관리자 게시판 저장

	@Override
	public BoardDTO getAdminBoardCont(int board_no) {
		return this.adminBoardDao.getAdminBoardCont(board_no);
	}//관리자 게시판 내용보기

	@Override
	public void editAdminBoard(BoardDTO eb) {
		this.adminBoardDao.editAdminBoard(eb);
	}//관리자 게시판수정

	@Override
	public void deleteAdminBoard(int board_no) {
		this.adminBoardDao.delAdminBoard(board_no);
	}//관리자 게시판 삭제
}
