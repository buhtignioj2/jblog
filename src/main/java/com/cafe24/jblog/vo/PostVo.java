package com.cafe24.jblog.vo;

public class PostVo {
    private Long no;
    private String title;
    private String content;
    private String reg_date;
    private Long categoryNo;
    
    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getReg_date() {
        return reg_date;
    }
    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
    public Long getCategoryNo() {
        return categoryNo;
    }
    public void setCategoryNo(Long categoryNo) {
        this.categoryNo = categoryNo;
    }
}
