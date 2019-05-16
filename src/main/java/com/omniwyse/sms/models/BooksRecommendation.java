package com.omniwyse.sms.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "books_grade_recommendations")
public class BooksRecommendation {
	private long id;   
	private long recommendation_id;
	private long bookid; 
	private long gradeid;
	@Transient
	private long classroomid;
	private String rating;	
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

	public long getRecommendation_id() {
		return recommendation_id;
	}

	public void setRecommendation_id(long recommendation_id) {
		this.recommendation_id = recommendation_id;
	}

	public long getBookid() {
		return bookid;
	}

	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	
	public long getGradeid() {
		return gradeid;
	}
	
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	@Transient
	public long getClassroomid() {
		return classroomid;
	}
	
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}
	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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
