package net.daum.dao;

import java.util.List;

import net.daum.dto.BoardDTO;
import net.daum.dto.GongjiDTO;

public interface AdminGongjiDAO {

	int getTotalCount(GongjiDTO findB);
	List<BoardDTO> getGongjiList(GongjiDTO findB);
	void adminGongjiInsert(GongjiDTO g);
	GongjiDTO getAdminGongjiCont(int gongji_no);
	void editAdminGongji(GongjiDTO eg);
	void deleteAdminGongji(int gongji_no);

}
