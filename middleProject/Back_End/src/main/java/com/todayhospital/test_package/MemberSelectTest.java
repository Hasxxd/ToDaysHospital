package com.todayhospital.test_package;

import com.todayhospital.dao.MemberDAO;
import com.todayhospital.dao.MemberDAOImpl;
import com.todayhospital.dto.MemberDTO;
import com.todayhospital.mybatis.config.DBService;

import java.util.List;

public class MemberSelectTest {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAOImpl(DBService.SqlSessionFactory());

        List<MemberDTO> list = dao.getAllMembers();

        if (list == null || list.isEmpty()) {
            System.out.println("조회된 회원이 없습니다.");
            return;
        }

        System.out.println("회원 목록 (" + list.size() + "명):");
        // for (MemberDTO m : list) {
        // System.out.printf("%s | 로그인ID: %s | 이름: %s%n",
        // m.getMemId(),
        // m.getMemLoginId(),
        // m.getMemName());
        // }
    }
}
