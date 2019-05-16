package com.omniwyse.sms.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Table;

@Table(name = "books_inventory")
public class MasterBookInventory {
	private long id;
	private long bookid;
	private long ownerid;
	private boolean available;
	private Date availableDate;
	private BigDecimal bookValue;
	private long rentalValue;
	private long buyflag;
	private boolean rentflag; 
	private String whereisthebook;
	private long locationid;
	private Timestamp createdon;
	private Timestamp  modifiedon;
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
	public long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Date getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
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
	public long getBuyflag() {
		return buyflag;
	}
	public void setBuyflag(long buyflag) {
		this.buyflag = buyflag;
	}
	public boolean isRentflag() {
		return rentflag;
	}
	public void setRentflag(boolean rentflag) {
		this.rentflag = rentflag;
	}
	public String getWhereisthebook() {
		return whereisthebook;
	}
	public void setWhereisthebook(String whereisthebook) {
		this.whereisthebook = whereisthebook;
	}
	public long getLocationid() {
		return locationid;
	}
	public void setLocationid(long locationid) {
		this.locationid = locationid;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	public Timestamp getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}
}
