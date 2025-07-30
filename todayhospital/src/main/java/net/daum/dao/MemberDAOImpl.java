package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;
import net.daum.mybatis.config.DBService;

public class MemberDAOImpl implements MemberDAO {

    private SqlSession sqlSession;

    public MemberDAOImpl() {
        this.sqlSession = DBService.SqlSessionFactory().openSession(true);
    }

    @Override
    public MemberDTO idCheck(String loginId) {
        return sqlSession.selectOne("idCheck", loginId);
    }

    @Override
    public List<ZipcodeDTO> zipFind(String dong) {
        return sqlSession.selectList("zipFind", dong);
    }

    @Override
    public void insertMember(MemberDTO member) {
        sqlSession.insert("insertMember", member);
    }

    @Override
    public MemberDTO findMemberForPwdReset(MemberDTO member) {
        return sqlSession.selectOne("findMemberForPwdReset", member);
    }

    @Override
    public void updatePassword(MemberDTO member) {
        sqlSession.update("updatePassword", member);
    }

    @Override
    public MemberDTO loginCheck(String loginId, String encryptedPassword) {
        java.util.Map<String, String> param = new java.util.HashMap<>();
        param.put("patientLoginId", loginId);
        param.put("patientPw", encryptedPassword);
        return sqlSession.selectOne("loginCheck", param);
    }

    @Override
    public MemberDTO getMemberById(String loginId) {
        return sqlSession.selectOne("getMemberById", loginId);
    }

    @Override
    public void updateMember(MemberDTO member) {
        sqlSession.update("updateMember", member);
    }

    @Override
    public void deleteMember(MemberDTO member) {
        sqlSession.update("deleteMember", member);
    }

    @Override
    public void incrementLoginFailCount(String loginId) {
        sqlSession.update("incrementLoginFailCount", loginId);
    }

    @Override
    public void resetLoginFailCount(String loginId) {
        sqlSession.update("resetLoginFailCount", loginId);
    }
}