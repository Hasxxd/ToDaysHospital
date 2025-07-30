package net.daum.dao;

import java.util.List;

import net.daum.dto.GongjiDTO;

public interface GongjiDAO {

	List<GongjiDTO> getGongjiList();
	GongjiDTO getGongCont(int gongji_no);

}
