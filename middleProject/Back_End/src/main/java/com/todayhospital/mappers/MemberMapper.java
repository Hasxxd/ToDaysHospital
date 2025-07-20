package com.todayhospital.mappers;

import java.util.List;
import com.todayhospital.dto.MemberDTO;

public interface MemberMapper {

    List<MemberDTO> selectAllMembers();

    MemberDTO selectMemberById(String mem_id);

    MemberDTO loginCheck(String mem_login_id, String mem_pw);

    List<MemberDTO> findMembersByName(String name);

    MemberDTO findById(String memberId);

    MemberDTO pwdMember(MemberDTO m);

    MemberDTO idCheck(String id);
}