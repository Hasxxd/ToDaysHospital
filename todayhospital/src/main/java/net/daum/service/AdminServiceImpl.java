package net.daum.service;

import net.daum.dao.AdminDAO;
import net.daum.dao.AdminDAOImpl;
import net.daum.dto.AdminDTO;

public class AdminServiceImpl implements AdminService {

	/* 관리자 정보 저장과 관리자 로그인 인증 ServiceImpl */
	
	private AdminDAO adminDao = AdminDAOImpl.getInstance();

	@Override
	public void insertAdmin(AdminDTO ab) {		
		this.adminDao.insertAdmin(ab);
	}//관리자 정보 저장

	@Override
	public AdminDTO adminLogin(String admin_id) {
		return this.adminDao.adminLogin(admin_id);
	}//관리자 로그인 인증
	
}
