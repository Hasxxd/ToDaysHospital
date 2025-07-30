package net.daum.mybatis.config;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.daum.dto.MemberDTO;
import net.daum.mappers.PatientMapper;

public class PatientSelectTest {

    public static void main(String[] args) {
        try (SqlSession session = DBService.SqlSessionFactory().openSession()) {
            System.out.println("[INFO] DB 연결 성공");

            PatientMapper mapper = session.getMapper(PatientMapper.class);
            if (mapper == null) {
                System.err.println("[ERROR] PatientMapper 주입 실패");
                return;
            }

            List<MemberDTO> members = mapper.findAllPatients();

            if (members == null || members.isEmpty()) {
                System.out.println("[INFO] 환자 데이터가 없습니다.");
                return;
            }

            System.out.println("========= 환자 목록 =========");
            for (MemberDTO member : members) {
                System.out.printf("  ID       : %s%n", member.getPatientId());
                System.out.printf("  이름      : %s%n", member.getPatientName());
                System.out.printf("  이메일    : %s%n", member.getPatientEMail());
                System.out.printf("  상태      : %s%n", member.getPatientState());
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            System.err.println("[ERROR] DB 연결 또는 쿼리 실행 중 예외 발생:");
            e.printStackTrace();
        }
    }
}