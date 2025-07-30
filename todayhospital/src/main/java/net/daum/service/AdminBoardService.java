package net.daum.service;

import java.util.List;

import net.daum.dto.BoardDTO;

public interface AdminBoardService {

	int getListCount(BoardDTO findB);
	List<BoardDTO> getBoardList(BoardDTO findB);
	void boardInsert(BoardDTO boarddata);
	BoardDTO getAdminBoardCont(int board_no);
	void editAdminBoard(BoardDTO eb);
	void deleteAdminBoard(int board_no);

}
