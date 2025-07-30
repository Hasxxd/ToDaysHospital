package net.daum.service;

import java.util.List;

import net.daum.dao.BbsDAO;
import net.daum.dao.BbsDAOImpl;
import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;

public class BbsServiceImpl implements BbsService {
	
	/* 사용자 자료실 ServiceImpl */

	private BbsDAO bbsDao = BbsDAOImpl.getInstance();

	@Override
	public int seqNumberNext() {
		return this.bbsDao.seqNumberNext();
	}//newbbs_no_seq 시퀀스로 부터 다음 시퀀스 번호값 가져오기

	@Override
	public void bbsInsert(BbsDTO bbs) {
		this.bbsDao.bbsInsert(bbs);
	}//tbl_newbbs 테이블에 자료실 저장

	@Override
	public void updateAttached_file(int attachedNumber, int bbs_no) {
		this.bbsDao.updateAttached_file(attachedNumber, bbs_no);
	}//첨부파일이 있는 경우만 bbs_attached_file 컬럼 레코드를 7로 수정=> 7이 있다면 첨부파일이 있는 경우이고 null이면 없다는 의미

	@Override
	public void insertFile(FileDTO fileDto) {
		this.bbsDao.insertFile(fileDto);
	}//한개 또는 다중파일 업로드한 파일정보 저장

	@Override
	public int getRowCount(BbsDTO findB) {
		return this.bbsDao.getRowCount(findB);
	}//검색 전 총 레코드 개수/검색후 레코드 개수

	@Override
	public List<BbsDTO> getBbsList(BbsDTO findB) {
		return this.bbsDao.getBbsList(findB);
	}//검색 전 총 페이징 목록 /검색후 페이징 목록

	@Override
	public BbsDTO getBbsCont(int bbs_no) {
		return this.bbsDao.getBbsCont(bbs_no);
	}// 내용보기 일때만 조회수 증가

	@Override
	public BbsDTO getBbsCont2(int bbs_no) {
		return this.bbsDao.getBbsCont2(bbs_no);
	}//수정폼+답변폼+삭제폼 -> 조회수 증가 안함

	@Override
	public List<FileDTO> getFileInfo(int bbs_no) {
		return this.bbsDao.getFileInfo(bbs_no);
	}//자료실 번호를 기준으로 첨부파일 정보를 읽어온다

	@Override
	public void bbsReply(BbsDTO bbsdata) {
		this.bbsDao.bbsReply(bbsdata);
	}//답변 레벨 증가와 답변 저장

	@Override
	public void editBbs(BbsDTO bbs) {
		this.bbsDao.editBbs(bbs);
	}//자료실 번호를 기준으로 글쓴이,글제목,글내용만 수정

	@Override
	public void delFileList(int bbs_no) {
		this.bbsDao.delFileList(bbs_no);
	}//기존 첨부된 파일정보를 tbl_newbbs_file 테이블로 부터 삭제

	@Override
	public void bbsDelete(int bbs_no) {
		this.bbsDao.bbsDelete(bbs_no);
	}//번호를 기준으로 tbl_newbbs 테이블로 부터 자료 삭제
}
