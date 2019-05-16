package com.omniwyse.sms.utils;

import java.math.BigDecimal;

public class MasterBookInventoryOut {
	private Long id;
	private String title;
	private  BigDecimal bookValue;
	private long rentalValue;
	private boolean available;
	private String whereisthebook;
	private long gradeid;
	private String sectionname;
	
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
	public BigDecimal getBookValue() {
		return bookValue;
	}
	public void setBookValue(BigDecimal bookValue) {
		this.bookValue = bookValue;
	}
	public long getRentalValue() {
		return rentalValue;
	}
	public void setRentalValue(long rentalValue) {
		this.rentalValue = rentalValue;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getWhereisthebook() {
		return whereisthebook;
	}
	public void setWhereisthebook(String whereisthebook) {
		this.whereisthebook = whereisthebook;
	}
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
}
