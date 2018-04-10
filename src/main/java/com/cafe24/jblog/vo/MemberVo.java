package com.cafe24.jblog.vo;

public class MemberVo {
    private String id;
    private String name;
    private String password;
    private String joinDate;
    
    @Override
    public String toString() {
	return "MemberVo [id=" + id + ", name=" + name + ", password=" + password + ", joinDate=" + joinDate + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
