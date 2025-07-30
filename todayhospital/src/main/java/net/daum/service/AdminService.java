package net.daum.service;

import net.daum.dto.AdminDTO;

public interface AdminService {

	void insertAdmin(AdminDTO ab);
	AdminDTO adminLogin(String admin_id);

}
