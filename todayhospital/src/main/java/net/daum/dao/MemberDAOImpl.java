package net.daum.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import net.daum.dto.MemberDTO;
import net.daum.dto.ZipcodeDTO;
import net.daum.mybatis.config.DBService;

public class MemberDAOImpl implements MemberDAO {

    private SqlSession sqlSession;

    public MemberDAOImpl() {
        sqlSession = DBService.SqlSessionFactory().openSession(true);
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
    public MemberDTO pwdMember(MemberDTO member) {
        return sqlSession.selectOne("pwdMember", member);
    }

    @Override
    public void updatePwd(MemberDTO member) {
        sqlSession.update("updatePwd", member);
    }

    @Override
    public MemberDTO loginCheck(String loginId) {
        return sqlSession.selectOne("loginCheck", loginId);
    }

    @Override
    public MemberDTO getMember(String loginId) {
        return sqlSession.selectOne("getMember", loginId);
    }

    @Override
    public void editMember(MemberDTO member) {
        sqlSession.update("editMember", member);
    }

    @Override
    public void delMem(MemberDTO member) {
        sqlSession.update("delMem", member);
    }
}