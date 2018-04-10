package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {
    @Autowired
    private SqlSession sqlSession;

    public boolean insert(BlogVo vo) {
	return sqlSession.insert( "blog.insert", vo ) == 1;
    }

    public BlogVo getInfo(String id) {
	
	return ( sqlSession.selectOne( "blog.getInfo", id ) );
    }

    public void update(BlogVo blogVo) {
	sqlSession.update( "blog.update", blogVo );
    }

}
