package net.daum.service;

import java.util.List;

import net.daum.dto.MemberDTO;

public interface AdminMemberService {

	int getMemberCount(MemberDTO findB);
	List<MemberDTO> getMemberList(MemberDTO findB);
	MemberDTO getAdminMemberInfo(String mem_id);
	void editMember(MemberDTO m);
	void delMember(String mem_id);

}
