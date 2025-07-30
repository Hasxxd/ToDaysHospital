package net.daum.dao;

import java.util.List;

import net.daum.dto.BoardDTO;

public interface BoardDAO {

	int getListCount(BoardDTO findB);
	List<BoardDTO> getBoardList(BoardDTO findB);
	void insertBoard(BoardDTO board);
	BoardDTO getBoardCont(int board_no);
	void updateHit(int board_no);
	void boardReply(BoardDTO boarddata);
	void editBoard(BoardDTO eb);
	void deleteBoard(int board_no);

}
