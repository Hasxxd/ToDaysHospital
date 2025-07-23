package com.todayhospital.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.todayhospital.dto.PatientDTO;
import com.todayhospital.mappers.PatientMapper;

public class PatientDAOImpl implements PatientDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public PatientDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.selectAllPatients();
        }
    }

    @Override
    public PatientDTO idCheck(String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.idCheck(id); // MyBatis XML에 idCheck 쿼리 필요
        }
    }

    @Override
    public PatientDTO pwdPatient(PatientDTO m) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.pwdPatient(m); // 이름, 전화번호 등으로 찾는 로직 가정
        }
    }

    public PatientDTO loginCheck(String id, String pw) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.loginCheck(id, pw);
        }
    }

    @Override
    public PatientDTO findById(String patientId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.findById(patientId);
        }
    }

    public PatientDTO loginCheck(Map<String, String> params) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.todayhospital.mappers.PatientMapper.loginCheck", params);
        }
    }
}
