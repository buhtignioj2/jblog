package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.exception.MemberDaoException;
import com.cafe24.jblog.vo.MemberVo;

@Repository
public class MemberDao {
    @Autowired
    private SqlSession sqlSession;
    
    public MemberVo get( MemberVo vo ) throws MemberDaoException {
	return sqlSession.selectOne( "member.getByIdAndPassword", vo );
    }
    
    public MemberVo get( String vo ) throws MemberDaoException {
   	return sqlSession.selectOne( "member.getById", vo );
    }
       
    public boolean join( MemberVo vo ) {
	return sqlSession.insert( "member.insert", vo ) == 1;
    }
}
