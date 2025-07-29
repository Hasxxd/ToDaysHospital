package net.daum.dao;

import java.util.List;

import net.daum.dto.BoardDTO;

public interface AdminBoardDAO {

	int getTotalCount(BoardDTO findB);
	List<BoardDTO> getBoardList(BoardDTO findB);
	void adminBoardInsert(BoardDTO boarddata);
	BoardDTO getAdminBoardCont(int board_no);
	void editAdminBoard(BoardDTO eb);
	void delAdminBoard(int board_no);

}
