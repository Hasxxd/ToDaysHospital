package net.daum.service;

import java.util.List;

import net.daum.dto.GongjiDTO;

public interface GongjiService {

	List<GongjiDTO> getGongjiList();
	GongjiDTO getGCont(int gongji_no);

}
