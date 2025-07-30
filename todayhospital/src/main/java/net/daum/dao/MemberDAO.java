package net.daum.dao;

import java.util.List;
import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;

public interface MemberDAO {

    MemberDTO idCheck(String loginId);
    List<ZipcodeDTO> zipFind(String dong);
    void insertMember(MemberDTO member);
    MemberDTO pwdMember(MemberDTO member);
    void updatePwd(MemberDTO member);
    MemberDTO loginCheck(String loginId);
    MemberDTO getMember(String loginId);
    void editMember(MemberDTO member);
    void delMem(MemberDTO member);
}