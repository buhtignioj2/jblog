package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.MemberDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.MemberVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {

    private static String SAVE_PATH = "/uploads";

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PostDao postDao;
    //
    // public Map<String, Object> getAll(String id, Long categoryNo, Long postNo) {
    // List<BlogVo> list = blogDao.getAll(id, categoryNo, postNo);
    //
    // Map<String, Object> map = new HashMap<String, Object>();
    // return map;
    // }
    //

    // public List<Map<?, ?>> getAll(String id, Long categoryNo, Long postNo) {
    //
    // Map<?, ?> map = blogDao.getAll(id, categoryNo, postNo);
    // return map;
    // }
    //

    // public BlogVo getById(String id) {
    // MemberVo authUser = MemberDao.idExist( id );
    // if ( authUser != null ) {
    // id = authUser.getId();
    // }
    // BlogVo blog = blogDao.get( id );
    // return blog;
    // }

    public BlogVo getInfo(String id) {
	return blogDao.getInfo( id );
    }

    public void restore(MultipartFile multipartFile, BlogVo blogVo) {
	String saveFileName = "";
	try {
	    if ( multipartFile.isEmpty() == true ) {
		blogDao.update( blogVo );
		return ;
	    }

	    String orgFileName = multipartFile.getOriginalFilename();
	    String extName = orgFileName.substring( orgFileName.lastIndexOf( '.' ) + 1 );
	    saveFileName = generateSaveFileName( extName );

	    writeFile( multipartFile, saveFileName );
	    blogVo.setLogo( saveFileName );
	    blogDao.update( blogVo );
	    
	} catch ( IOException e ) {
	    e.printStackTrace();
	}

    }

    private String generateSaveFileName(String extName) {
	String fileName = "";
	Calendar calendar = Calendar.getInstance();
	fileName += calendar.get( Calendar.YEAR );
	fileName += calendar.get( Calendar.MONTH );
	fileName += calendar.get( Calendar.DATE );
	fileName += calendar.get( Calendar.HOUR );
	fileName += calendar.get( Calendar.MINUTE );
	fileName += calendar.get( Calendar.SECOND );
	fileName += calendar.get( Calendar.MILLISECOND );
	fileName += "." + extName;
	return fileName;
    }

    private void writeFile(MultipartFile file, String saveFileName) throws IOException {
	byte[] fileData = file.getBytes();
	FileOutputStream fos = new FileOutputStream( SAVE_PATH + "/" + saveFileName );
	fos.write( fileData );
	fos.close();
    }

}
