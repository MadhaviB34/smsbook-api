package com.omniwyse.sms.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Book;
import com.omniwyse.sms.utils.BookDTO;


@Service("bookService")
public class BookService{

	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;
   
	private Database db;
	 
	public Book createBook(long tenantId, BookDTO bookDTO) {
		db = retrieve.getDatabase(tenantId);		
		Book book = new Book();		
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbncode(bookDTO.getIsbncode());
		book.setGeneres(bookDTO.getGeneres());
		book.setEdition(bookDTO.getEdition());
		book.setYear(bookDTO.getYear());
		book.setBookcoverpage(bookDTO.getBookcoverpage());
		book.setSamplepageurl(bookDTO.getSamplepageurl());
		book.setImagesUrl1(bookDTO.getImagesUrl1());
		book.setImagesUrl2(bookDTO.getImagesUrl2());
		book.setBookcondition(bookDTO.getBookcondition());
		book.setBookprice(bookDTO.getBookprice());
		book.setBaserentalvalue(bookDTO.getBaserentalvalue());
		book.setBookdescription(bookDTO.getBookdescription());
		book.setDemand(bookDTO.getDemand());
		book.setNumberofpages(bookDTO.getNumberofpages());
		book.setProductgroup(bookDTO.getProductgroup());
		book.setPublicationdate(bookDTO.getPublicationdate());
		book.setPublisher(bookDTO.getPublisher());
		book.setCreatedon(new Date());
		book.setModifiedon(new Date());
		db.insert(book).getRowsAffected();
		return book;
	}
	
	public Book updateBookById(long tenantId,long bookId, BookDTO bookDTO) {
		db = retrieve.getDatabase(tenantId);
        Book book = db.where("id=?", bookId).results(Book.class).get(0);
        book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbncode(bookDTO.getIsbncode());
		book.setGeneres(bookDTO.getGeneres());
		book.setEdition(bookDTO.getEdition());
		book.setYear(bookDTO.getYear());
		book.setBookcoverpage(bookDTO.getBookcoverpage());
		book.setSamplepageurl(bookDTO.getSamplepageurl());
		book.setImagesUrl1(bookDTO.getImagesUrl1());
		book.setImagesUrl2(bookDTO.getImagesUrl2());
		book.setBookcondition(bookDTO.getBookcondition());
		book.setBookprice(bookDTO.getBookprice());
		book.setBaserentalvalue(bookDTO.getBaserentalvalue());
		book.setBookdescription(bookDTO.getBookdescription());
		book.setDemand(bookDTO.getDemand());
		book.setNumberofpages(bookDTO.getNumberofpages());
		book.setProductgroup(bookDTO.getProductgroup());
		book.setPublicationdate(bookDTO.getPublicationdate());
		book.setPublisher(bookDTO.getPublisher());
		db.update(book).getRowsAffected();
		return book;
	}
	
	
	public List<Book> getBookById(long tenantId, long bookId) {
      db = retrieve.getDatabase(tenantId);		
      return db.sql("select * from booksinfo where id= ?", bookId).results(Book.class);
	}
	
	public List<Book> getBookList(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql("select * from booksinfo").results(Book.class);
	}

}
