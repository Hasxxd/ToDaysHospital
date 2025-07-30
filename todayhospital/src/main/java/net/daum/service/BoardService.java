package net.daum.service;

import java.util.List;

import net.daum.dto.BoardDTO;

public interface BoardService {

	int getListCount(BoardDTO findB);
	List<BoardDTO> getBoardList(BoardDTO findB);
	void boardInsert(BoardDTO boarddata);
	BoardDTO getBoardCont(int board_no);
	void updateHit(int board_no);
	void boardReply(BoardDTO boarddata);

}
