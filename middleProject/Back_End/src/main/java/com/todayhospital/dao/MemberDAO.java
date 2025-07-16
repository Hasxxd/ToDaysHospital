package com.todayhospital.dao;

import java.util.List;
import com.todayhospital.dto.MemberDTO;

public interface MemberDAO {

  MemberDTO idCheck(String id);

  MemberDTO loginCheck(String id);

  MemberDTO getMember(String id);

  MemberDTO pwdMember(MemberDTO m);

}