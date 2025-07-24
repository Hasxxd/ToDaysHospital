package com.middleproject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.middleproject.dto.PatientDTO;
import com.middleproject.mappers.PatientMapper;

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

    @Override
    public PatientDTO findById(String patientId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.findById(patientId);
        }
    }

    @Override
    public PatientDTO loginCheck(String patientLoginId, String patientPw) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.loginCheck(patientLoginId, patientPw);
        }
    }

}
