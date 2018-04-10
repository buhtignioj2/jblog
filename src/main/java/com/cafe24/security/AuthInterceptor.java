package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.MemberVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {

	if ( handler instanceof HandlerMethod == false ) {
	    // HandlerMethod 는 후에 실행할 컨트롤러의 메소드
	    // 메소드명, 인스턴스, 타입, 사용된 Annotation 들을 확인할 수 있다
	    return true;
	}

	HandlerMethod handlerMethod = ( HandlerMethod ) handler;

	Auth auth = handlerMethod.getMethodAnnotation( Auth.class );

	if ( auth == null ) {
	    return true;
	}

	HttpSession session = request.getSession();
	if ( session == null ) {
	    response.sendRedirect( request.getContextPath() + "/member/login" );
	    return false;
	}

	MemberVo authUser = ( MemberVo ) session.getAttribute( "authUser" );
	if ( authUser == null ) {
	    response.sendRedirect( request.getContextPath() + "/member/login" );
	    return false;
	}

	// role 가져오기
	Auth.Role role = auth.role();

	// User Role 접근이면 인증되어 있기 때문에
	// 허용
	if ( role == Auth.Role.USER ) {
	    return true;
	}

	// 8. BLOG ID 접근 체크
	// Blog 주인(ADMIN)이 아니면
	// main 화면으로 리다이렉트

	if ( request.getRequestURI().contains( "/" + authUser.getId() + "/" ) == false ) {
	    response.sendRedirect( request.getContextPath() );
	    return false;
	}

	// 9. Blog 주인(ADMIN) 접근 허용
	return true;
    }

}
