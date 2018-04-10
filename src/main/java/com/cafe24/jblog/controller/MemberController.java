package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.MemberService;
import com.cafe24.jblog.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private BlogService blogService;
    
    @RequestMapping( value = "/login", method = RequestMethod.GET )
    public String login() {
	return "/member/login";
    }

    @RequestMapping( value="/join", method=RequestMethod.GET)
    public String join() {
	return "/member/join";
    }

    @RequestMapping( value="/join", method=RequestMethod.POST)
    public String join( @ModelAttribute MemberVo vo ) {
	memberService.join( vo );
	return "/member/joinsuccess";
    }
    
}
