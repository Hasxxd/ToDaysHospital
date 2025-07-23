package com.todayhospital.test_package;

import com.todayhospital.dao.PatientDAO;
import com.todayhospital.dao.PatientDAOImpl;
import com.todayhospital.dto.PatientDTO;
import com.todayhospital.mybatis.config.DBService;

import java.util.List;

public class PatientSelectTest {
    public static void main(String[] args) {
        PatientDAO dao = new PatientDAOImpl(DBService.SqlSessionFactory());

        List<PatientDTO> list = dao.getAllPatients();

        if (list == null || list.isEmpty()) {
            System.out.println("조회된 회원이 없습니다.");
            return;
        }

        System.out.println("회원 목록 (" + list.size() + "명):");
        for (PatientDTO m : list) {
            System.out.printf("%s | 로그인ID: %s | 이름: %s%n",
                    m.getPatient_id(),
                    m.getPatient_login_id(),
                    m.getPatient_name());
        }

    }
}
