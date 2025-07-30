package net.daum.dao;

import java.util.List;
import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;

public interface MemberDAO {
    List<ZipcodeDTO> zipFind(String dong);
    
    MemberDTO idCheck(String loginId);
    MemberDTO findMemberForPwdReset(MemberDTO member);
    MemberDTO loginCheck(String loginId, String encryptedPassword);
    MemberDTO getMemberById(String loginId);

    void insertMember(MemberDTO member);
    void updatePassword(MemberDTO member);
    void updateMember(MemberDTO member);
    void deleteMember(MemberDTO member);
    void incrementLoginFailCount(String loginId);
    void resetLoginFailCount(String loginId);
}