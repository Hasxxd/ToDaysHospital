package com.middleproject.test_space;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.middleproject.dto.PatientDTO;
import com.middleproject.mappers.PatientMapper;
import com.middleproject.mybatis.config.DBService;

public class PatientSelectTest {

    public static void main(String[] args) {
        // DB 연결 테스트
        try (SqlSession session = DBService.SqlSessionFactory().openSession()) {
            System.out.println("[INFO] DB 연결 성공");

            // 매퍼 인터페이스 획득
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            if (mapper == null) {
                System.err.println("[ERROR] PatientMapper 주입 실패");
                return;
            }

            // 테스트용 쿼리 실행: 전체 환자 조회
            List<PatientDTO> patients = mapper.findAllPatients();

            if (patients != null && !patients.isEmpty()) {
                System.err.println("---------------------------------");
                System.out.println("[INFO] 환자 목록 조회 결과:");
                for (PatientDTO patient : patients) {
                    System.out.println(" - ID : " + patient.getPatientId()
                            + "\n - Name : " + patient.getPatientName()
                            + "\n - Email : " + patient.getPatientEmail()
                            + "\n - State : " + patient.getPatientState());
                }
                System.err.println("---------------------------------");
            } else {
                System.out.println("[INFO] 환자 데이터가 없습니다.");
            }

        } catch (Exception e) {
            System.err.println("[ERROR] DB 연결 또는 쿼리 실행 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
