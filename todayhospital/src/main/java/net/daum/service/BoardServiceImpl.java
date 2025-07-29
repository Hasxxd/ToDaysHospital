package net.daum.service;

import java.util.List;

import net.daum.dao.BoardDAO;
import net.daum.dao.BoardDAOImpl;
import net.daum.dto.BoardDTO;

public class BoardServiceImpl implements BoardService {
	
	/*  사용자 게시판 ServiceImpl */

	private BoardDAO boardDao = BoardDAOImpl.getInstance();

	@Override
	public int getListCount(BoardDTO findB) {
		return this.boardDao.getListCount(findB);
	}//검색 전 총레코드 개수, 검색후 레코드 개수

	@Override
	public List<BoardDTO> getBoardList(BoardDTO findB) {
		return this.boardDao.getBoardList(findB);
	}//검색 전 총 목록, 검색 후 목록

	@Override
	public void boardInsert(BoardDTO board) {
		this.boardDao.insertBoard(board);
	}//게시판 저장	

	@Override
	public BoardDTO getBoardCont(int board_no) {
		return this.boardDao.getBoardCont(board_no);
	}//번호에 해당하는 게시판 내용보기

	@Override
	public void updateHit(int board_no) {
		this.boardDao.updateHit(board_no);
	}//내용보기 일때만 조회수 증가

	@Override
	public void boardReply(BoardDTO boarddata) {
		this.boardDao.boardReply(boarddata);
	}//관리자 답변 저장

	public void editBoard(BoardDTO eb) {
		this.boardDao.editBoard(eb);
	}//게시판 수정

	public void boardDelete(int board_no) {
		this.boardDao.deleteBoard(board_no);
	}//번호를 기준으로 삭제
}
