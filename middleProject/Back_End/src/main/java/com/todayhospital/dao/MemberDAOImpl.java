package com.todayhospital.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
// import com.todayhospital.mybatis.config.DBService;
import com.todayhospital.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
    public MemberDAOImpl() {
    } // 생성자

    @Override
    public MemberDTO idCheck(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'idCheck'");
    }

    @Override
    public MemberDTO loginCheck(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginCheck'");
    }

    @Override
    public MemberDTO getMember(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMember'");
    }

    @Override
    public MemberDTO pwdMember(MemberDTO m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pwdMember'");
    }

}