package com.todayhospital.service;

// import org.apache.ibatis.session.SqlSession;
// import org.apache.ibatis.session.SqlSessionFactory;

import com.todayhospital.dao.PatientDAO;
import com.todayhospital.dao.PatientDAOImpl;
import com.todayhospital.dto.PatientDTO;
import com.todayhospital.mybatis.config.DBService;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO patientDAO;

    public PatientServiceImpl() {
        this.patientDAO = new PatientDAOImpl(DBService.SqlSessionFactory());
    }

    @Override
    public PatientDTO login(String patientId, String password) {
        PatientDTO patient = patientDAO.findById(patientId);
        if (patient != null && patient.getPatient_pw() != null &&
                patient.getPatient_pw().equals(password)) {
            return patient;
        }
        return null;
    }

    @Override
    public PatientDTO loginCheck(String id, String pw) {
        return patientDAO.loginCheck(id, pw);
    }

    // @Override
    // public PatientDTO getPatient(String id) {
    // return patientDAO.getPatient(id); // DAO 호출만
    // }

    @Override
    public PatientDTO idCheck(String id) {
        return patientDAO.idCheck(id);
    }

    @Override
    public PatientDTO pwdPatient(PatientDTO m) {
        return patientDAO.pwdPatient(m);
    }
}
