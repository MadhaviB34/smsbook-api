package com.omniwyse.sms.controller;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.config.RequestInfo;
import com.omniwyse.sms.models.Book;
import com.omniwyse.sms.services.BookService;
import com.omniwyse.sms.utils.BookDTO;
import com.omniwyse.sms.utils.JSONResultEntity;
import com.omniwyse.sms.utils.SMSAPIConstants;

@RestController
@RequestMapping("/{tenantId}")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestInfo.class);
	
	@Autowired
	private BookService bookService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/api/books", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> addBook(@PathVariable("tenantId") long tenantId, @RequestBody BookDTO bookDTO) {     
		logger.debug(" Start creat a Book in booksinfo");
		try {
			Book book = bookService.createBook(tenantId, bookDTO);
            JSONResultEntity<Book> response = new JSONResultEntity<Book>(
                    true, SMSAPIConstants.CREATE_SUCCESSFUL, null, 
                    Arrays.asList(book));
            logger.debug("End of Create a Book");
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } catch (Exception e) {
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                    false, e.getMessage(), null, 
                    Arrays.asList(SMSAPIConstants.CREATE_FAILED));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateBookById(@PathVariable("tenantId") long tenantId, @PathVariable("bookId") long bookId, @RequestBody BookDTO bookDTO) {
		logger.debug(" Start update a Book in booksinfo");
        try {
        	Book book = bookService.updateBookById(tenantId, bookId, bookDTO);
            if (book != null) {
            JSONResultEntity<Book> response = new JSONResultEntity<Book>(
                    true, SMSAPIConstants.UPDATE_SUCCESSFUL, null,
                    Arrays.asList(book));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } else {
            String responseEntity = "No Book found by given bookId:" + bookId;
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                        false, "No book found", null, Arrays.asList(responseEntity));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                    false, e.getMessage(), null,
                    Arrays.asList(SMSAPIConstants.UPDATE_FAILED));
            return new ResponseEntity<JSONResultEntity<?>>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/books/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBookById(@PathVariable("tenantId") long tenantId,@PathVariable("bookId") long bookId) {
		 logger.debug("Start call getBookById() ");
		 List<Book> bookList = null;
	     try {
	    	 bookList = bookService.getBookById(tenantId, bookId);
	         JSONResultEntity<Book> response = new JSONResultEntity<Book>(
	                 true, SMSAPIConstants.FOUND_SUCCESSFUL, null, bookList);
	         return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
	     } catch (Exception e) {
	         logger.error("Exception while geting BookList from DB  - "
	                 + e.getMessage(), e);
	         JSONResultEntity<Book> response = new JSONResultEntity<Book>(
	                 false, SMSAPIConstants.FOUND_FAILED, null, bookList);
	         return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/api/books", method = RequestMethod.GET)
	public ResponseEntity<?> getBookList(@PathVariable("tenantId") long tenantId) {
		 logger.debug("Start call getBookList() ");
	        List<Book> bookList = null;
	        try {
	        	bookList = bookService.getBookList(tenantId);
	            JSONResultEntity<Book> response = new JSONResultEntity<Book>(
	                    true, SMSAPIConstants.FOUND_SUCCESSFUL, null, bookList);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.OK);
	        } catch (Exception e) {
	            logger.error("Exception while geting BookList from DB  - "
	                    + e.getMessage(), e);
	            JSONResultEntity<Book> response = new JSONResultEntity<Book>(
	                    false, SMSAPIConstants.FOUND_FAILED, null, bookList);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}
}
