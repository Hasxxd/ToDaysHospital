package com.todayhospital.service;

import com.todayhospital.dto.MemberDTO;

public interface MemberService {
    MemberDTO idCheck(String id);

    MemberDTO pwdMember(MemberDTO m);

    MemberDTO loginCheck(String id, String pw);

    // MemberDTO getMember(String id);

    MemberDTO login(String memberId, String password);
}
