package com.todayhospital.test_;

import com.todayhospital.dao.MemberDAO;
import com.todayhospital.dao.MemberDAOImpl;
import com.todayhospital.dto.MemberDTO;
import com.todayhospital.mybatis.config.DBService;

import java.util.List;

public class MemberSelectTest {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAOImpl(DBService.SqlSessionFactory());
        // MemberDAO dao = new MemberDAOImpl(DBService.getFactory());
        List<MemberDTO> list = dao.getAllMembers();
        System.out.println(list);
        // for (MemberDTO m : list) {
        // System.out.println(m.getMemId() + " | " + m.getMemLoginId() + " | " +
        // m.getMemName());
        // }
    }
}