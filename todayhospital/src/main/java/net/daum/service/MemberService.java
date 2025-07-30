package net.daum.service;

import java.util.List;

import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;

public interface MemberService {

    // 아이디 중복 확인
    MemberDTO idCheck(String loginId);

    // 우편번호 검색
    List<ZipcodeDTO> zipFind(String dong);

    // 회원 가입
    void insertMember(MemberDTO member);

    // 비밀번호 찾기용 회원 조회
    MemberDTO findMemberForPwdReset(MemberDTO member);

    // 비밀번호 업데이트
    void updatePassword(MemberDTO member);

    // 로그인 인증
    MemberDTO loginCheck(String loginId, String encryptedPassword);

    // 회원 단건 조회
    MemberDTO getMemberById(String loginId);

    // 회원정보 수정
    void updateMember(MemberDTO member);

    // 회원 삭제 (탈퇴)
    void deleteMember(MemberDTO member);

    // 로그인 실패 횟수 초기화
    void resetLoginFailCount(String loginId);

    // 로그인 실패 횟수 증가
    void incrementLoginFailCount(String loginId);
}