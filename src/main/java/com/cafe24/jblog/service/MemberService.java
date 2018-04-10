package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.MemberDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.MemberVo;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private BlogDao blogDao;
    
    @Autowired
    private CategoryDao categoryDao;
    
    public MemberVo getUser(MemberVo vo) {
	return memberDao.get( vo );
    }

    public void join( MemberVo vo ) {
	boolean result = false;
	
	result = memberDao.join( vo ) == true;	
	
	if( result == false ) {
	    System.out.println( "아이디 생성 실패" );
	    return;
	}
	
	BlogVo blogVo = new BlogVo();
	blogVo.setId( vo.getId() );
	blogVo.setTitle( vo.getId() + " 의 블로그" );
	blogVo.setLogo( "logo.jpg" );
	
	result = blogDao.insert( blogVo ) == true;
	
	if( result == false ) {
	    System.out.println( "블로그 생성 실패" );
	    return;
	}
	
	CategoryVo categoryVo = new CategoryVo();
	categoryVo.setName( "미분류" );
	categoryVo.setDescription( " " );
	categoryVo.setblogID( blogVo.getId() );
	
	result = categoryDao.insert( categoryVo ) == true;
	
	if( result == false ) {
	    System.out.println( "카테고리 생성 실패" );
	    return;
	}
    }
    
}
