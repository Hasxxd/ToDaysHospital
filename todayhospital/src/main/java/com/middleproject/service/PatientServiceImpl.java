package com.middleproject.service;

import com.middleproject.dao.PatientDAO;
import com.middleproject.dao.PatientDAOImpl;
import com.middleproject.dto.LoginDTO;
import com.middleproject.dto.PatientDTO;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO patientDAO;

    public PatientServiceImpl() {
        this.patientDAO = new PatientDAOImpl();
    }

    @Override
    public PatientDTO login(LoginDTO loginDTO) {
        return patientDAO.loginCheck(loginDTO);
    }

    @Override
    public boolean isAccountLocked(String loginId) {
        return patientDAO.isAccountLocked(loginId);
    }

    @Override
    public void increaseLoginFailCount(String loginId) {
        patientDAO.increaseLoginFailCount(loginId);
    }

    @Override
    public void resetLoginFailCount(String loginId) {
        patientDAO.resetLoginFailCount(loginId);
    }
}
