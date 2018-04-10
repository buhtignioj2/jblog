package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.vo.CategoryVo;

@Controller( "categoryAPIController" )
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
 
}
