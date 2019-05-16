package com.omniwyse.sms.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "books_classroom_inventory")
public class ClassroomBooks {	
	private long id; 
	private long bookid; 
	private long gradeid; 
    private long classroom_id; 
	private BigDecimal total_stock;
	private BigDecimal avl_stock;
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
	public long getClassroom_id() {
		return classroom_id;
	}
	public void setClassroom_id(long classroom_id) {
		this.classroom_id = classroom_id;
	}
	public BigDecimal getTotal_stock() {
		return total_stock;
	}
	public void setTotal_stock(BigDecimal total_stock) {
		this.total_stock = total_stock;
	}
	public BigDecimal getAvl_stock() {
		return avl_stock;
	}
	public void setAvl_stock(BigDecimal avl_stock) {
		this.avl_stock = avl_stock;
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
