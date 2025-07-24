package com.middleproject.test_space;

import java.util.List;

import com.middleproject.dao.PatientDAO;
import com.middleproject.dao.PatientDAOImpl;
import com.middleproject.dto.PatientDTO;
import com.middleproject.mybatis.config.DBService;

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
                    m.getPatientId(),
                    m.getPatientLoginId(),
                    m.getPatientName());
        }
    }
}
