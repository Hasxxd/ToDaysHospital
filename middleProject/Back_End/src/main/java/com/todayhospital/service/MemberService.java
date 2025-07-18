package com.todayhospital.service;

import com.todayhospital.dto.MemberDTO;

public interface MemberService {
    MemberDTO idCheck(String id);

    MemberDTO pwdMember(MemberDTO m);

    MemberDTO loginCheck(String id);

    MemberDTO getMember(String id);
}
