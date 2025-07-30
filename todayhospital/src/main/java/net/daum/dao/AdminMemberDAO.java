package net.daum.dao;

import java.util.List;

import net.daum.dto.MemberDTO;

public interface AdminMemberDAO {

	int getMemberCount(MemberDTO findB);
	List<MemberDTO> getMemberList(MemberDTO findB);
	MemberDTO getAdminMemberInfo(String mem_id);
	void editMember(MemberDTO m);
	void delMem(String mem_id);

}
