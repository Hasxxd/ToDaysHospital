package com.middleproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;
import com.middleproject.mybatis.config.DBService;
import com.middleproject.mappers.PatientMapper;

public class PatientDAOImpl implements PatientDAO {

    private final SqlSessionFactory sqlSessionFactory = DBService.SqlSessionFactory();

    @Override
    public PatientDTO loginCheck(LoginDTO loginDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.loginCheck(loginDTO);
        }
    }

    @Override
    public void increaseLoginFailCount(String patientLoginId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            mapper.incrementLoginFailCount(patientLoginId);
            session.commit();
        }
    }

    @Override
    public void resetLoginFailCount(String patientLoginId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            mapper.resetLoginFailCount(patientLoginId);
            session.commit();
        }
    }

    @Override
    public boolean isAccountLocked(String patientLoginId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            int locked = mapper.isAccountLocked(patientLoginId);
            return locked == 1;
        }
    }

    @Override
    public boolean idExists(String patientLoginId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            PatientDTO dto = mapper.findByLoginId(patientLoginId);
            return dto != null;
        }
    }

    @Override
    public PatientDTO findByPatientId(String patientId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.findByPatientId(patientId);
        }
    }

    @Override
    public PatientDTO findPatientByNameAndPhone(PatientDTO queryDto) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PatientMapper mapper = session.getMapper(PatientMapper.class);
            return mapper.findPatientByNameAndPhone(queryDto);
        }
    }
}
