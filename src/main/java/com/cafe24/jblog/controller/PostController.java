package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.PostService;

public class PostController {
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private PostService postService;
    
}
