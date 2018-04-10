package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.vo.CategoryVo;


@Service
public class CategoryService {
    
    @Autowired
    private CategoryDao categoryDao;
    
    
    public List<CategoryVo> getCategory(String id) {
	return categoryDao.getCategory(id);
    }
    
    public Long gettotalPost(String id) {
	Long totalPost = categoryDao.gettotalPost(id);
	return totalPost;
    }

    public CategoryVo insert(CategoryVo categoryVo) {
	categoryDao.insert( categoryVo );
	return categoryVo;
    }

    public boolean delete(Long categoryno) {
	return categoryDao.delete( categoryno ) == 1;
    }
}
