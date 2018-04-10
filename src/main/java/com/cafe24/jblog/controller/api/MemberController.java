package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.repository.MemberDao;
import com.cafe24.jblog.vo.MemberVo;

@Controller( "userAPIController" )
@RequestMapping( "/api/member" )
public class MemberController {

    @Autowired
    private MemberDao memberDao;
    
    @ResponseBody
    @RequestMapping( "/checkeid" )
    public JSONResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
	MemberVo vo = memberDao.get( id );
	return JSONResult.success( vo == null ? "not extist" : "exist" );
    }
}
