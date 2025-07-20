package com.todayhospital.service;

// import org.apache.ibatis.session.SqlSession;
// import org.apache.ibatis.session.SqlSessionFactory;

import com.todayhospital.dao.MemberDAO;
import com.todayhospital.dao.MemberDAOImpl;
import com.todayhospital.dto.MemberDTO;
import com.todayhospital.mybatis.config.DBService;

public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    public MemberServiceImpl() {
        this.memberDAO = new MemberDAOImpl(DBService.SqlSessionFactory());
    }

    @Override
    public MemberDTO login(String memberId, String password) {
        MemberDTO member = memberDAO.findById(memberId);
        if (member != null && member.getPassword() != null &&
                member.getPassword().equals(password)) {
            return member;
        }
        return null;
    }

    @Override
    public MemberDTO loginCheck(String id, String pw) {
        return memberDAO.loginCheck(id, pw);
    }

    // @Override
    // public MemberDTO getMember(String id) {
    // return memberDAO.getMember(id); // DAO 호출만
    // }

    @Override
    public MemberDTO idCheck(String id) {
        return memberDAO.idCheck(id);
    }

    @Override
    public MemberDTO pwdMember(MemberDTO m) {
        return memberDAO.pwdMember(m);
    }
}
