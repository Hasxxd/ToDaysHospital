package net.daum.dao;

import net.daum.dto.AdminDTO;

public interface AdminDAO {

	void insertAdmin(AdminDTO ab);
	AdminDTO adminLogin(String admin_id);

}
