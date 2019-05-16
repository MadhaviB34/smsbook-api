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
import com.omniwyse.sms.models.BooksStudentAssign;
import com.omniwyse.sms.services.BooksStudentAssignService;
import com.omniwyse.sms.utils.BooksStudentAssignDTO;
import com.omniwyse.sms.utils.BooksStudentAssignOut;
import com.omniwyse.sms.utils.JSONResultEntity;
import com.omniwyse.sms.utils.SMSAPIConstants;

@RestController
@RequestMapping("/{tenantId}")
public class BooksStudentAssignController {

	private static final Logger logger = LoggerFactory.getLogger(RequestInfo.class);
	
	@Autowired
	private BooksStudentAssignService booksStudentAssignService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/student/books/assign", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> addStudentBooks(@PathVariable("tenantId") long tenantId, @RequestBody BooksStudentAssignDTO booksStudentAssignDTO) {
		logger.debug(" Start creat a BooksStudentAssign ");
		try {
			BooksStudentAssign booksStudentAssign = booksStudentAssignService.createBooksStudentAssign(tenantId, booksStudentAssignDTO);
            JSONResultEntity<BooksStudentAssign> response = new JSONResultEntity<BooksStudentAssign>(
                    true, SMSAPIConstants.CREATE_SUCCESSFUL, null, Arrays.asList(booksStudentAssign));
            logger.debug("End of BooksStudentAssign");
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } catch (Exception e) {
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                    false, SMSAPIConstants.CREATE_FAILED, null, Arrays.asList(SMSAPIConstants.CREATE_FAILED));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/student/books/assign/{studentBookId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateStudentBooksById(@PathVariable("tenantId") long tenantId, @PathVariable("studentBookId") long studentBookId, @RequestBody BooksStudentAssignDTO booksStudentAssignDTO) {
		logger.debug(" Start update a BooksStudentAssign ");
        try {
        	BooksStudentAssign booksStudentAssign = booksStudentAssignService.updateStudentBookAssignById(tenantId, studentBookId, booksStudentAssignDTO);
            if (booksStudentAssign != null) {
            JSONResultEntity<BooksStudentAssign> response = new JSONResultEntity<BooksStudentAssign>(
                    true, SMSAPIConstants.UPDATE_SUCCESSFUL, null, Arrays.asList(booksStudentAssign));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } else {
            String responseEntity = "No BookStudentAssign found by given studentBookId:" + studentBookId;
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                        false, "No BookStudentAssign found", null, Arrays.asList(responseEntity));
            return new ResponseEntity<JSONResultEntity<?>>(response,HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                    false, e.getMessage(), null,Arrays.asList(SMSAPIConstants.UPDATE_FAILED));
            return new ResponseEntity<JSONResultEntity<?>>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/view/assigned/books", method = RequestMethod.GET)
	public ResponseEntity<?> viewAssignedBooks(@PathVariable("tenantId") long tenantId) {
		 logger.debug("Start call viewAssignedBooks() ");
	        List<BooksStudentAssignOut> booksStudentAssignList = null;
	        try {
	        	booksStudentAssignList = booksStudentAssignService.viewAssignedBooks(tenantId);
	            JSONResultEntity<BooksStudentAssignOut> response = new JSONResultEntity<BooksStudentAssignOut>(
	                    true, SMSAPIConstants.FOUND_SUCCESSFUL, null, booksStudentAssignList);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.OK);
	        } catch (Exception e) {
	            logger.error("Exception while geting assignedBookList from DB  - "
	                    + e.getMessage(), e);
	            JSONResultEntity<BooksStudentAssignOut> response = new JSONResultEntity<BooksStudentAssignOut>(
	                    false, SMSAPIConstants.FOUND_FAILED, null, booksStudentAssignList);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}
	
}
