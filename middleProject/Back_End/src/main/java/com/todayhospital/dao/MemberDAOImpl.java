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

    @Override
    public MemberDTO idCheck(String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.idCheck(id); // MyBatis XML에 idCheck 쿼리 필요
        }
    }

    @Override
    public MemberDTO pwdMember(MemberDTO m) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.pwdMember(m); // 이름, 전화번호 등으로 찾는 로직 가정
        }
    }

    public MemberDTO loginCheck(String id, String pw) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.loginCheck(id, pw);
        }
    }

    @Override
    public MemberDTO findById(String memberId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.findById(memberId);
        }
    }
}
