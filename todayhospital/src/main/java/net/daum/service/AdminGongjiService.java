package net.daum.service;

import java.util.List;

import net.daum.dto.BoardDTO;
import net.daum.dto.GongjiDTO;

public interface AdminGongjiService {

	int getListCount(GongjiDTO findB);
	List<BoardDTO> getGongjiList(GongjiDTO findB);
	void adminGongjiInsert(GongjiDTO g);
	GongjiDTO getAdminGongjiCont(int gongji_no);
	void editAdminGongji(GongjiDTO eg);
	void deleteAdminGongji(int gongji_no);

}
