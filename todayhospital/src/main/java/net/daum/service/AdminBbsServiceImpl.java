package net.daum.service;

import java.util.List;

import net.daum.dao.AdminBbsDAO;
import net.daum.dao.AdminBbsDAOImpl;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;

public class AdminBbsServiceImpl implements AdminBbsService {
	
	/* 관리자 자료실 ServiceImpl */

	private AdminBbsDAO adminBbsDao = AdminBbsDAOImpl.getInstance();

	@Override
	public int getRowCount(BbsDTO findB) {
		return this.adminBbsDao.getRowCount(findB);
	}//검색전 총게시물수와 검색후 게시물수

	@Override
	public List<BbsDTO> getBbsList(BbsDTO findB) {
		return this.adminBbsDao.getBbsList(findB);
	}//검색전 총목록과 검색후 목록

	@Override
	public int seqNumberNext() {
		return this.adminBbsDao.seqNumberNext();
	}//newbbs_no_seq.NEXTVAL 다음 시퀀스 번호값 반환

	@Override
	public void bbsInsert(BbsDTO bbs) {
		this.adminBbsDao.bbsInsert(bbs);
	}//자료실 저장

	@Override
	public void updateAttached_file(int attachedNumber, int bbs_no) {
		this.adminBbsDao.updateAttached_file(attachedNumber, bbs_no);
	}//첨부파일이 있는 경우만 bbs_attached_file 컬럼 레코드를 7로 수정=> 7이 있다면 첨부파일이 있는 경우이고 null이면 없다는 의미

	@Override
	public void insertFile(FileDTO fileDto) {
		this.adminBbsDao.insertFile(fileDto);
	}//한개 또는 다중파일 업로드한 파일정보 저장

	@Override
	public BbsDTO getAdminBbsCont(int bbs_no) {
		return this.adminBbsDao.getAdminBbsCont(bbs_no);
	}//관리자 자료실 상세정보 보기와 수정폼

	@Override
	public List<FileDTO> getFileInfo(int bbs_no) {
		return this.adminBbsDao.getFileInfo(bbs_no);
	}//번호에 해당하는 파일리스트 정보

	@Override
	public void editBbs(BbsDTO bbs) {
		this.adminBbsDao.editBbs(bbs);
	}//번호를 기준으로 글쓴이,글제목,글내용만 수정

	@Override
	public void delFileList(int bbs_no) {
		this.adminBbsDao.delFileList(bbs_no);
	}//기존 첨부된 파일정보를 tbl_newbbs_file 테이블로 부터 삭제

	@Override
	public void adminBbsDelete(int bbs_no) {
		this.adminBbsDao.adminBbsDelete(bbs_no);
	}//번호를 기준으로 관리자 자료실 삭제	
}
