package com.omniwyse.sms.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "multiple_grade_assigned_to_classroom")
public class MultipleGradesForClassroomBooks {
	private long id ;
	private long bookclsrmid; 
	private long gradeid;
	private Date createdon;
	private Date modifiedon;
	
	@Id
    @GeneratedValue    
	public long getId() {
		return id;
	}
    public void setId(long id) {
		this.id = id;
	}
	public long getBookclsrmid() {
		return bookclsrmid;
	}
	public void setBookclsrmid(long bookclsrmid) {
		this.bookclsrmid = bookclsrmid;
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
	public Date getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}
 
}
