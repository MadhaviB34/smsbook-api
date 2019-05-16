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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.config.RequestInfo;
import com.omniwyse.sms.services.MasterBookInventoryService;
import com.omniwyse.sms.utils.JSONResultEntity;
import com.omniwyse.sms.utils.MasterBookInventoryOut;
import com.omniwyse.sms.utils.SMSAPIConstants;

@RestController
@RequestMapping("/{tenantId}")
public class MasterBookInventoryController {

	private static final Logger logger = LoggerFactory.getLogger(RequestInfo.class);
	
	@Autowired
	private MasterBookInventoryService masterBookInventoryservice;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/api/masterbooks", method = RequestMethod.GET)
	public ResponseEntity<?> getInventoryBooksByClassroomAndSection(@PathVariable("tenantId") long tenantId) {
		 logger.debug("Start call getBooksByClassroomId() ");
		 List<MasterBookInventoryOut> masterBookInventoryList = null;
		 try {
			 masterBookInventoryList = masterBookInventoryservice.getInventoryBooksByClassroomIdAndSection(tenantId);
	            if (masterBookInventoryList != null) {
		            JSONResultEntity<MasterBookInventoryOut> response = new JSONResultEntity<MasterBookInventoryOut>(
		            true, SMSAPIConstants.FOUND_SUCCESSFUL, null, masterBookInventoryList);
		            return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
	            } else {
	                String responseEntity = "No MasterBookInventory found ";
	                JSONResultEntity<String> response = new JSONResultEntity<String>(
	                false, SMSAPIConstants.FOUND_FAILED, null, Arrays.asList(responseEntity));
	                return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.BAD_REQUEST);
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
