package com.todayhospital.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.todayhospital.dto.MemberDTO;
import com.todayhospital.mappers.MemberMapper;

public class MemberDAO {
    private SqlSessionFactory sqlSessionFactory;

    public MemberDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<MemberDTO> getAllMembers() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.selectAllMembers();
        }
    }
}
