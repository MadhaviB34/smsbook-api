package com.omniwyse.sms.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "booksinfo")
public class Book {
	private long id;   
	private String title;
	private String author;
	private String isbncode;
	private String generes;
	private String edition;
	private Date year;
	private String bookcoverpage;
	private String samplepageurl;
	private String imagesUrl1;
	private String imagesUrl2;
	private String bookcondition;
	private String bookprice;
	private String baserentalvalue;
	private String bookdescription;
	private int demand;
	private int numberofpages;
	private int productgroup;
	private Date publicationdate;
	private String publisher;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbncode() {
		return isbncode;
	}
	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}
	public String getGeneres() {
		return generes;
	}
	public void setGeneres(String generes) {
		this.generes = generes;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public String getBookcoverpage() {
		return bookcoverpage;
	}
	public void setBookcoverpage(String bookcoverpage) {
		this.bookcoverpage = bookcoverpage;
	}
	public String getSamplepageurl() {
		return samplepageurl;
	}
	public void setSamplepageurl(String samplepageurl) {
		this.samplepageurl = samplepageurl;
	}
	public String getImagesUrl1() {
		return imagesUrl1;
	}
	public void setImagesUrl1(String imagesUrl1) {
		this.imagesUrl1 = imagesUrl1;
	}
	public String getImagesUrl2() {
		return imagesUrl2;
	}
	public void setImagesUrl2(String imagesUrl2) {
		this.imagesUrl2 = imagesUrl2;
	}
	public String getBookcondition() {
		return bookcondition;
	}
	public void setBookcondition(String bookcondition) {
		this.bookcondition = bookcondition;
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
	public String getBookdescription() {
		return bookdescription;
	}
	public void setBookdescription(String bookdescription) {
		this.bookdescription = bookdescription;
	}
	public int getDemand() {
		return demand;
	}
	public void setDemand(int demand) {
		this.demand = demand;
	}
	public int getNumberofpages() {
		return numberofpages;
	}
	public void setNumberofpages(int numberofpages) {
		this.numberofpages = numberofpages;
	}
	public int getProductgroup() {
		return productgroup;
	}
	public void setProductgroup(int productgroup) {
		this.productgroup = productgroup;
	}
	public Date getPublicationdate() {
		return publicationdate;
	}
	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
