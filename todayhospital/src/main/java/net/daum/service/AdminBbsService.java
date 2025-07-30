package net.daum.service;

import java.util.List;

import net.daum.dto.BbsDTO;
import net.daum.dto.FileDTO;

public interface AdminBbsService {

	int getRowCount(BbsDTO findB);
	List<BbsDTO> getBbsList(BbsDTO findB);
	int seqNumberNext();
	void bbsInsert(BbsDTO bbs);
	void updateAttached_file(int i, int seq_result);
	void insertFile(FileDTO fileDto);
	BbsDTO getAdminBbsCont(int bbs_no);
	List<FileDTO> getFileInfo(int bbs_no);
	void editBbs(BbsDTO bbs);
	void delFileList(int bbs_no);
	void adminBbsDelete(int bbs_no);

}
