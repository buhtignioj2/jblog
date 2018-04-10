package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
    @Autowired
    private SqlSession sqlSession;

    public PostVo getPost(String id, Long categoryNo) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put( "id", id );
	map.put( "categoryNo", categoryNo );

	return sqlSession.selectOne( "post.getPostLatest", map );
    }

    public List<PostVo> getPostAll(String id, Long categoryNo) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put( "id", id );
	map.put( "categoryNo", categoryNo );

	return sqlSession.selectList( "post.getPostAll", map );
    }

    public void insertPost(PostVo postVo) {
	sqlSession.insert( "post.insertPost", postVo );
    }
}
