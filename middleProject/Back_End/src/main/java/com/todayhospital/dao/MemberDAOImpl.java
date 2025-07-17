package com.todayhospital.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.todayhospital.dto.MemberDTO;
import com.todayhospital.mappers.MemberMapper;

public class MemberDAOImpl implements MemberDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MemberDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.selectAllMembers();
        }
    }
}
