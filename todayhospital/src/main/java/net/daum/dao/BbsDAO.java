package net.daum.dao;

import java.util.List;

import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;

public interface BbsDAO {

	int seqNumberNext();
	void bbsInsert(BbsDTO bbs);
	void updateAttached_file(int attachedNumber, int bbs_no);
	void insertFile(FileDTO fileDto);
	int getRowCount(BbsDTO findB);
	List<BbsDTO> getBbsList(BbsDTO findB);
	BbsDTO getBbsCont(int bbs_no);
	BbsDTO getBbsCont2(int bbs_no);
	List<FileDTO> getFileInfo(int bbs_no);
	void bbsReply(BbsDTO bbsdata);
	void editBbs(BbsDTO bbs);
	void delFileList(int bbs_no);
	void bbsDelete(int bbs_no);

}
