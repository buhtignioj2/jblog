package com.cafe24.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping("/{id:(?!assets|uploads).*}")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;

    @RequestMapping("")
    public String main(@PathVariable String id, Model model) {
	// 아이디를 바탕으로 블로그 기본정보 불러오기 ( 타이틀, 로고)
	BlogVo blogVo = blogService.getInfo( id );

	// 유저번호를 바탕으로 카테고리 목록 가져오기
	List<CategoryVo> categoryList = categoryService.getCategory( blogVo.getId() );

	// 최신글 하나만 가져오기
	PostVo postVo = postService.getPost( blogVo.getId(), null );
	List<PostVo> postVoList = postService.getPostAll( blogVo.getId(), null );
	
	
	// 어트리뷰터 셋팅
	model.addAttribute( "view", "main" );
	model.addAttribute( "postVo", postVo );
	model.addAttribute( "postList", postVoList );
	model.addAttribute( "id", id );
	model.addAttribute( "blogVo", blogVo );
	model.addAttribute( "categoryList", categoryList );
	return "blog/blog-main";
    }
    
    @RequestMapping("/{categoryNo}/{postNo}")
    public String category(@PathVariable String id, @PathVariable("categoryNo") Long categoryNo, @PathVariable("postNo") Long postNo, Model model ) {
	// 아이디를 바탕으로 블로그 기본정보 불러오기 ( 타이틀, 로고)
	BlogVo blogVo = blogService.getInfo( id );

	// 유저번호를 바탕으로 카테고리 목록 가져오기
	List<CategoryVo> categoryList = categoryService.getCategory( blogVo.getId() );

	// 최신글 하나만 가져오기
	PostVo postVo = postService.getPost( blogVo.getId(), categoryNo );
	List<PostVo> postVoList = postService.getPostAll( blogVo.getId(), categoryNo );
	
	
	for( CategoryVo vo : categoryList ) {
	    System.out.println( "########################" + vo );
	}
	
	// 어트리뷰터 셋팅
	model.addAttribute( "view", "main" );
	model.addAttribute( "postVo", postVo );
	model.addAttribute( "postList", postVoList );
	model.addAttribute( "id", id );
	model.addAttribute( "blogVo", blogVo );
	model.addAttribute( "categoryList", categoryList );
	
	return "blog/blog-main";
    }

    // @RequestMapping({ "" })
    // public String main(@PathVariable String id) {
    // return "blog/blog-main";
    // }

    // @RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
    // public String blogMain( @PathVariable String id, @PathVariable Optional<Long>
    // pathNo1, @PathVariable Optional<Long> pathNo2, ModelMap modelMap ) {
    //
    // Long categoryNo = 0L;
    // Long postNo = 0L;
    //
    // if( pathNo2.isPresent() ) {
    // postNo = pathNo2.get();
    // categoryNo = pathNo1.get();
    // } else if( pathNo1.isPresent() ){
    // categoryNo = pathNo1.get();
    // }
    //
    // modelMap.putAll( ( Map<? extends String, ? extends Object> )
    // blogService.getAll( id, categoryNo, postNo ) );
    // return "blog/blog-main";
    // }

    @Auth(role = Role.ADMIN)
    @RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
    public String adminBasic(@PathVariable("id") String id, Model model) {
	 BlogVo blogVo = blogService.getInfo(id);
	 model.addAttribute("blogVo", blogVo);
	return "blog/blog-admin-basic";
    }

    @Auth(role = Role.ADMIN)
    @RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
    public String adminBasic(@PathVariable("id") String id, @ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file) {
	blogVo.setId( id );
	blogService.restore( file, blogVo );
	return "redirect:/" + id;
    }

    @Auth(role = Role.ADMIN)
    @RequestMapping( value = "/admin/category", method = RequestMethod.GET)
    public String adminCategory( @PathVariable("id") String id, Model model) {
	
	List<CategoryVo> categoryList = categoryService.getCategory( id );
	Long totalPost = categoryService.gettotalPost( id );
	model.addAttribute( "categoryList", categoryList );
	model.addAttribute( "totalPost", totalPost );
	return "blog/blog-admin-category";
    }
    
    @Auth(role = Role.ADMIN)
    @RequestMapping( value = "/admin/category", method = RequestMethod.POST)
    public String adminCategory( @PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo, Model model ) {
	return "blog/blog-admin-category";
    }

    @Auth(role = Role.ADMIN)
    @RequestMapping("/admin/write")
    public String adminWrite( @PathVariable("id") String id, Model model ) {
	List<CategoryVo> categoryList = categoryService.getCategory( id );
	model.addAttribute( "categoryList", categoryList );
	return "blog/blog-admin-write";
    }
    
    @Auth(role = Role.ADMIN)
    @RequestMapping( value = "/admin/write", method = RequestMethod.POST)
    public String adminWrite(@PathVariable("id")String id, @ModelAttribute PostVo postVo, Model model) {
	postService.write(postVo);
	return "redirect:/" + id;
    }
    
    @Auth(role = Role.ADMIN)
    @ResponseBody
    @RequestMapping( value = "/api/category/addcategory", method = RequestMethod.GET)
    public JSONResult addCategory( @PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo, Model model) {
	categoryVo.setblogID( id );
	CategoryVo vo = categoryService.insert(categoryVo);
	
	return JSONResult.success( vo == null ? "not extist" : "exist" );
    }
    
    @Auth(role = Role.ADMIN)
    @ResponseBody
    @RequestMapping( value = "/api/category/removecategory", method = RequestMethod.GET)
    public JSONResult addCategory( @PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo ) {
	categoryVo.setblogID( id );
	categoryVo.getNo();
	
	return JSONResult.success( categoryService.delete(categoryVo.getNo()) == false ? "not extist" : "exist" );
    }
}
