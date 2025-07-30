package net.daum.service;

import java.util.List;

import net.daum.dao.GongjiDAO;
import net.daum.dao.GongjiDAOImpl;
import net.daum.dto.GongjiDTO;


public class GongjiServiceImpl implements GongjiService {
	
	/* 사용자 공지사항 ServiceImpl */

	private GongjiDAO gongjiDao = GongjiDAOImpl.getInstance();

	@Override
	public List<GongjiDTO> getGongjiList() {
		return this.gongjiDao.getGongjiList();
	}//사용자 공지목록

	@Override
	public GongjiDTO getGCont(int gongji_no) {
		return this.gongjiDao.getGongCont(gongji_no);
	}//사용자 공지내용

}
