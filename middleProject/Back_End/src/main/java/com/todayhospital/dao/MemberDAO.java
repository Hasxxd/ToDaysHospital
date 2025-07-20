package com.todayhospital.dao;

import java.util.List;

import com.todayhospital.dto.MemberDTO;

/**
 * 회원 관련 DB 작업 인터페이스
 */
public interface MemberDAO {

    /** 전체 회원 목록 조회 */
    List<MemberDTO> getAllMembers();

    /** 아이디 중복 체크 */
    MemberDTO idCheck(String id);

    /** 비밀번호 찾기 (이름, 전화번호 등으로) */
    MemberDTO pwdMember(MemberDTO m);

    /** 로그인 아이디로 정보 조회 */
    MemberDTO loginCheck(String id, String pw);

    /** 회원 정보 단건 조회 (로그인용) */
    MemberDTO findById(String memberId);
}
