package net.daum.service;

import java.util.List;

import net.daum.dao.MemberDAO;
import net.daum.dao.MemberDAOImpl;
import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;

public class MemberServiceImpl implements MemberService {

    // DAO 의존성 선언
    private final MemberDAO memberDao = new MemberDAOImpl();

    // 아이디 중복 확인
    @Override
    public MemberDTO idCheck(String loginId) {
        return memberDao.idCheck(loginId);
    }

    // 우편번호 검색
    @Override
    public List<ZipcodeDTO> zipFind(String dong) {
        return memberDao.zipFind(dong);
    }

    // 회원 등록
    @Override
    public void insertMember(MemberDTO member) {
        memberDao.insertMember(member);
    }

    // 비밀번호 찾기용 회원 조회
    @Override
    public MemberDTO findMemberForPwdReset(MemberDTO member) {
        return memberDao.findMemberForPwdReset(member);
    }

    // 비밀번호 업데이트
    @Override
    public void updatePassword(MemberDTO member) {
        memberDao.updatePassword(member);
    }

    // 로그인 인증
    @Override
    public MemberDTO loginCheck(String loginId, String encryptedPassword) {
        return memberDao.loginCheck(loginId, encryptedPassword);
    }

    // 회원 조회
    @Override
    public MemberDTO getMemberById(String loginId) {
        return memberDao.getMemberById(loginId);
    }

    // 회원 정보 수정
    @Override
    public void updateMember(MemberDTO member) {
        memberDao.updateMember(member);
    }

    // 회원 탈퇴
    @Override
    public void deleteMember(MemberDTO member) {
        memberDao.deleteMember(member);
    }

    // 로그인 실패 횟수 증가
    @Override
    public void incrementLoginFailCount(String loginId) {
        memberDao.incrementLoginFailCount(loginId);
    }

    // 로그인 실패 횟수 초기화
    @Override
    public void resetLoginFailCount(String loginId) {
        memberDao.resetLoginFailCount(loginId);
    }
}