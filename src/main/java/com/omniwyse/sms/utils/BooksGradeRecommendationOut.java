package com.omniwyse.sms.utils;

import java.util.Date;

public class BooksGradeRecommendationOut {
	private long id;
	private String title;
	private String gradename;
	private String sectionname;
	private Date createdon;
	private long bookid;
	private long gradeid;	
	private long classroomid;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
}
