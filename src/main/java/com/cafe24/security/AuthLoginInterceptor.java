package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.MemberService;
import com.cafe24.jblog.vo.MemberVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	String id = request.getParameter( "id" );
	String password = request.getParameter( "password" );
	
	MemberVo vo = new MemberVo();
	vo.setId( id );
	vo.setPassword( password );
	
	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext( request.getServletContext() );

	MemberService memberService = ac.getBean( MemberService.class );
	MemberVo authUser = memberService.getUser( vo );
	
	if( authUser == null ) {
	    response.sendRedirect( request.getContextPath() + "/member/login" );
	    return false;
	}
	
	HttpSession session = request.getSession( true );
	session.setAttribute( "authUser", authUser );
	response.sendRedirect( request.getContextPath() + "" );

	return false;
    }

}
