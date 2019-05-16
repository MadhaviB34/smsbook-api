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
import com.omniwyse.sms.models.ClassroomBooks;
import com.omniwyse.sms.services.ClassroomBooksService;
import com.omniwyse.sms.utils.ClassroomBooksAssignedOut;
import com.omniwyse.sms.utils.ClassroomBooksDTO;
import com.omniwyse.sms.utils.JSONResultEntity;
import com.omniwyse.sms.utils.SMSAPIConstants;

@RestController
@RequestMapping("/{tenantId}")
public class ClassroomBooksController {

	private static final Logger logger = LoggerFactory.getLogger(RequestInfo.class);
	
	@Autowired
	private ClassroomBooksService classroomBookservice;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/api/clsrmbooksAssign", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> addClassroomBooks(@PathVariable("tenantId") long tenantId, @RequestBody ClassroomBooksDTO classroomBooksDTO) {     
		logger.debug(" Start creat a ClassroomBooksAssign");
		try {
			ClassroomBooks clsrmbooksAssign = classroomBookservice.createClsrmBooksAssign(tenantId, classroomBooksDTO);
            JSONResultEntity<ClassroomBooks> response = new JSONResultEntity<ClassroomBooks>(
                    true, SMSAPIConstants.CREATE_SUCCESSFUL, null, 
                    Arrays.asList(clsrmbooksAssign));
            logger.debug("End of Create a ClassroomBooksAssign");
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } catch (Exception e) {
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                    false, e.getMessage(), null, 
                    Arrays.asList(SMSAPIConstants.CREATE_FAILED));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/api/clsrmbooksAssign/{clsrmBookId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateClassroomBooksById(@PathVariable("tenantId") long tenantId, @PathVariable("clsrmBookId") long classroomBookId, @RequestBody ClassroomBooksDTO classroomBooksDTO) {
		logger.debug(" Start update a ClassroomBooksAssign");
        try {
        	ClassroomBooks clsrmbooks = classroomBookservice.updateClassroomBooksById(tenantId, classroomBookId, classroomBooksDTO);
            if (clsrmbooks != null) {
            JSONResultEntity<ClassroomBooks> response = new JSONResultEntity<ClassroomBooks>(
                    true, SMSAPIConstants.UPDATE_SUCCESSFUL, null,
                    Arrays.asList(clsrmbooks));
            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
            } else {
            String responseEntity = "No ClassroomBooks found by given classroomBookId:" + classroomBookId;
            JSONResultEntity<String> response = new JSONResultEntity<String>(
                        false, "No ClassroomBooks found", null, Arrays.asList(responseEntity));
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
	@RequestMapping(value="/classroom/books", method = RequestMethod.GET)
	public ResponseEntity<?> getBooksByClassroom(@PathVariable("tenantId") long tenantId) {
		 logger.debug("Start call getBooksByClassroomId() ");
		 List<ClassroomBooksAssignedOut> classroomBooks = null;
		 try {
			 classroomBooks = classroomBookservice.getBooksByClassroom(tenantId);
	            if (classroomBooks != null) {
	            JSONResultEntity<ClassroomBooksAssignedOut> response = new JSONResultEntity<ClassroomBooksAssignedOut>(
	                    true, SMSAPIConstants.FOUND_SUCCESSFUL, null, classroomBooks);
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.OK);
	            } else {
	                String responseEntity = "No ClassRoomBooks found by given classroomId:";
	                JSONResultEntity<String> response = new JSONResultEntity<String>(
	                        false, "No ClassRoomBooks found", null,
	                        Arrays.asList(responseEntity));
	                return new ResponseEntity<JSONResultEntity<?>>(response,
	                        HttpStatus.BAD_REQUEST);

	            }
	       } catch (Exception e) {
	            JSONResultEntity<String> response = new JSONResultEntity<String>(
	                    false, e.getMessage(), null,
	                    Arrays.asList(SMSAPIConstants.FOUND_FAILED));
	            return new ResponseEntity<JSONResultEntity<?>>(response,
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }
  }
}
