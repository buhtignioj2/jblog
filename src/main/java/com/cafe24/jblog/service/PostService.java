package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.PostVo;

@Service
public class PostService {
    
    @Autowired
    private PostDao postDao;
    
    public PostVo getPost(String id, Long categoryNo) {
	return postDao.getPost( id, categoryNo );
    }

    public List<PostVo> getPostAll(String id, Long categoryNo) {
	return postDao.getPostAll( id, categoryNo );
    }

    public void write(PostVo postVo) {
	postDao.insertPost( postVo );
    }
}
