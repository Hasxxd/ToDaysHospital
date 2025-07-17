package com.todayhospital.dao;

import com.todayhospital.dto.MemberDTO;

public interface MemberDAO {
    boolean existsById(String id); // ID 중복 체크용

    MemberDTO loginCheck(String id, String pw); // 로그인 검증

    MemberDTO getMember(String id); // 회원정보 조회

    int updatePassword(String id, String newPw); // 비밀번호 변경
}
