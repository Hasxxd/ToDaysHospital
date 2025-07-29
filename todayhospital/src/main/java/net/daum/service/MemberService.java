package net.daum.service;

import java.util.List;

import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;

public interface MemberService {

	MemberDTO idCheck(String id);
	List<ZipcodeDTO> zipFind(String dong);
	void insertMember(MemberDTO member);
	MemberDTO pwdMember(MemberDTO m);
	void updatePwd(MemberDTO m);
	MemberDTO loginCheck(String id);
	MemberDTO getMember(String id);
	void editMember(MemberDTO m);
	void delMem(MemberDTO dm);

}
