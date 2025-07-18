package com.todayhospital.dao;

import java.util.List;
import com.todayhospital.dto.MemberDTO;

public interface MemberDAO {
    List<MemberDTO> getAllMembers();
}
