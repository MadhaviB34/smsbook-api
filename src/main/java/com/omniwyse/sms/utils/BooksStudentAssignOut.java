package com.omniwyse.sms.utils;

import java.math.BigDecimal;
import java.util.Date;

public class BooksStudentAssignOut {

	private long id;
	private String title;
	private String name;
	private Date assigned_date;
	private Date returned_date;
	private Date expected_returned_date;
	private String book_status;
	private BigDecimal fee; 
	private String notes;
	private long sid;
	private long bookid;
	private long gradeid; 
	private long classroom_id; 
	
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	public long getClassroom_id() {
		return classroom_id;
	}
	public void setClassroom_id(long classroom_id) {
		this.classroom_id = classroom_id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAssigned_date() {
		return assigned_date;
	}
	public void setAssigned_date(Date assigned_date) {
		this.assigned_date = assigned_date;
	}
	public Date getReturned_date() {
		return returned_date;
	}
	public void setReturned_date(Date returned_date) {
		this.returned_date = returned_date;
	}
	public Date getExpected_returned_date() {
		return expected_returned_date;
	}
	public void setExpected_returned_date(Date expected_returned_date) {
		this.expected_returned_date = expected_returned_date;
	}
	public String getBook_status() {
		return book_status;
	}
	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
}
