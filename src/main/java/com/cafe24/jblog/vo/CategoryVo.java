package com.cafe24.jblog.vo;

public class CategoryVo {
    private Long no;
    private String name;
    private String description;
    private String create_date;
    private String blogID;
    
    @Override
    public String toString() {
	return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", create_date="
		+ create_date + ", blogID=" + blogID + "]";
    }
    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    public String getblogID() {
        return blogID;
    }
    public void setblogID(String blogID) {
        this.blogID = blogID;
    }
}
