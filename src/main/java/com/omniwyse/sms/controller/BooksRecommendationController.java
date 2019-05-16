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
import com.omniwyse.sms.models.BooksRecommendation;
import com.omniwyse.sms.services.BooksRecommendationService;
import com.omniwyse.sms.utils.BooksGradeRecommendationOut;
import com.omniwyse.sms.utils.BooksRecommendationDTO;
import com.omniwyse.sms.utils.JSONResultEntity;
import com.omniwyse.sms.utils.SMSAPIConstants;

@RestController
@RequestMapping("/{tenantId}")
public class BooksRecommendationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestInfo.class);
	
	@Autowired
	private  BooksRecommendationService bookRecService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/api/booksRec", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> addBook(@PathVariable("tenantId") long tenantId, @RequestBody BooksRecommendationDTO booksRecDTO) {     
		logger.debug(" Start creat a BookRecommendation in booksinfo");
		try {
			BooksRecommendation booksRec = bookRecService.createBookRec(tenantId, booksRecDTO);
            JSONResultEntity<BooksRecommendation> response = new JSONResultEntity<BooksRecommendation>(
                    true, SMSAPIConstants.CREATE_SUCCESSFUL, null, Arrays.asList(booksRec));
            logger.debug("End of Create a BookRecommendation");
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } catch (Exception e) {
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                    false, e.getMessage(), null, 
                    Arrays.asList(SMSAPIConstants.CREATE_FAILED));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "api/booksRec/{booksRecId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateBookRecById(@PathVariable("tenantId") long tenantId, @PathVariable("booksRecId") long booksRecId, @RequestBody BooksRecommendationDTO booksRecDTO) {
		logger.debug(" Start update a BookRecommendation in booksinfo");
        try {
        	BooksRecommendation booksRec = bookRecService.updateBookRecById(tenantId, booksRecId, booksRecDTO);
            if (booksRec != null) {
            JSONResultEntity<BooksRecommendation> response = new JSONResultEntity<BooksRecommendation>(
                    true, SMSAPIConstants.UPDATE_SUCCESSFUL, null, Arrays.asList(booksRec));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } else {
            String responseEntity = "No BookRecommendation found by given booksRecId:" + booksRecId;
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                        false, "No BookRecommendation found", null, Arrays.asList(responseEntity));
            return new ResponseEntity<JSONResultEntity<?>>(response,HttpStatus.BAD_REQUEST);
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
	@RequestMapping(value="/api/booksRec", method = RequestMethod.GET)
	public ResponseEntity<?> getBooksGradeRecommendationList(@PathVariable("tenantId") long tenantId) {
		 logger.debug("Start call getBooksRecommendationList() ");
	        List<BooksGradeRecommendationOut> booksRecList = null;
	        try {
	        	booksRecList = bookRecService.getBooksGradeRecommendationList(tenantId);
	            JSONResultEntity<BooksGradeRecommendationOut> response = new JSONResultEntity<BooksGradeRecommendationOut>(
	                    true, SMSAPIConstants.FOUND_SUCCESSFUL, null, booksRecList);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.OK);
	        } catch (Exception e) {
	            logger.error("Exception while geting BooksRecommendationList from DB  - "
	                    + e.getMessage(), e);
	            JSONResultEntity<BooksGradeRecommendationOut> response = new JSONResultEntity<BooksGradeRecommendationOut>(
	                    false, SMSAPIConstants.FOUND_FAILED, null, booksRecList);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}
}
