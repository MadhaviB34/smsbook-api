package com.omniwyse.sms.utils;

import java.util.Date;

public class ClassroomBooksAssignedOut {
	private Long id;
	private String title;
	private String gradename;
	private long gradeid;
	private String sectionname;
	private Date createdon;
	private long bookid;
	private long classroom_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public long getClassroom_id() {
		return classroom_id;
	}
	public void setClassroom_id(long classroom_id) {
		this.classroom_id = classroom_id;
	}
}
