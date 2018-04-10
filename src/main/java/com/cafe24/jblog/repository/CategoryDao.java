package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Repository
public class CategoryDao {
    @Autowired
    private SqlSession sqlSession;

    public boolean insert(CategoryVo categoryVo) {
	return sqlSession.insert( "category.insert", categoryVo ) == 1;
    }

    public List<CategoryVo> getCategory(String id) {
	return sqlSession.selectList( "category.getCategory", id );
    }

    public Long gettotalPost(String id) {
	return sqlSession.selectOne( "category.gettotalPost", id );
    }

    public int delete(Long categoryNo) {
	return sqlSession.delete( "category.delete", categoryNo );
    }
}
