package com.omniwyse.sms.utils;

import java.util.Date;
import java.util.List;

import com.omniwyse.sms.models.BooksStudentAssign;

public class ClassRoomBookInventory {

	private String title;
	private String gradename;
	private String sectionname;
	private long id;
	private long gradeid;
	private long bookid;
	private long classroom_id;
	private String bookprice;
	private String baserentalvalue;
	private Date createdon;
	private long count =1;
	private List<BooksStudentAssign> listOfAssignedBooks;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(long classroom_id) {
		this.classroom_id = classroom_id;
	}

	public String getBookprice() {
		return bookprice;
	}

	public void setBookprice(String bookprice) {
		this.bookprice = bookprice;
	}

	public String getBaserentalvalue() {
		return baserentalvalue;
	}

	public void setBaserentalvalue(String baserentalvalue) {
		this.baserentalvalue = baserentalvalue;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<BooksStudentAssign> getListOfAssignedBooks() {
		return listOfAssignedBooks;
	}

	public void setListOfAssignedBooks(List<BooksStudentAssign> listOfAssignedBooks) {
		this.listOfAssignedBooks = listOfAssignedBooks;
	}

}
