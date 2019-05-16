package com.omniwyse.sms.utils;

import java.io.Serializable;

public class BooksRecommendationDTO implements Serializable {

    private static final long serialVersionUID = 1L;	 
	
	private long id;   
	private long recommendation_id;
	private long bookid;  
	private long gradeid;
	private long classroomid;
	private String rating;

	public BooksRecommendationDTO() {
	}
	    
	public static long getSerialversionuid() {   
	   return serialVersionUID;
	}

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
}
