package com.omniwyse.sms.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "books_student_assign")
public class BooksStudentAssign {
	private long id;
	private long bookid; 
	private long sid; 
	private long gradeid; 
	private long classroom_id; 
	private Date assigned_date;
	private Date returned_date;
	private Date expected_returned_date;
	private String book_status;
	private BigDecimal fee; 
	private String notes;
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
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
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
	public String getBook_status() {
		return book_status;
	}
	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}
	public Date getExpected_returned_date() {
		return expected_returned_date;
	}
	public void setExpected_returned_date(Date expected_returned_date) {
		this.expected_returned_date = expected_returned_date;
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
		
}
