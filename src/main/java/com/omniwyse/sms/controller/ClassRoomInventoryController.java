package com.omniwyse.sms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.ClassRoomInventoryService;
import com.omniwyse.sms.utils.BooksStudentAssignOut;
import com.omniwyse.sms.utils.ClassRoomBookInventory;
import com.omniwyse.sms.utils.JSONResultEntity;
import com.omniwyse.sms.utils.SMSAPIConstants;

@RestController
@RequestMapping("/{tenantId}")
public class ClassRoomInventoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRoomInventoryController.class);

	@Autowired
	private ClassRoomInventoryService classRoomInventoryService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/classroomInventory/{gradeId}/{classroomId}")
	public ResponseEntity<?> getAssignedBooksByClassroom(@PathVariable("tenantId") long tenantId,
			@PathVariable("gradeId") long gradeId, @PathVariable("classroomId") long classroomId) {
		LOGGER.debug("Start : com.omniwyse.sms.controller.ClassRoomInventoryController.getAssignedBooksByClassroom() ");
		List<ClassRoomBookInventory> classroomBooks = null;
		try {
			classroomBooks = classRoomInventoryService.getAssignedBooksByClassroom(tenantId, gradeId, classroomId);
			if (classroomBooks != null) {
				JSONResultEntity<ClassRoomBookInventory> response = new JSONResultEntity<ClassRoomBookInventory>(true,
						SMSAPIConstants.FOUND_SUCCESSFUL, null, classroomBooks);
				return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
			} else {
				String responseMessage = "No assigned books found in the classroom";
				JSONResultEntity<String> response = new JSONResultEntity<String>(false, responseMessage, null,
						Arrays.asList(responseMessage));
				return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			LOGGER.debug("Exception occured :  " + e.getMessage());
			JSONResultEntity<String> response = new JSONResultEntity<String>(false, e.getMessage(), null,
					Arrays.asList(SMSAPIConstants.FOUND_FAILED));
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/classroomInventory/assignBooks")
	public ResponseEntity<?> assignBooksToStudents(@PathVariable("tenantId") long tenantId,@RequestBody ClassRoomBookInventory classRoomBookInventory) {
		LOGGER.debug("Start : com.omniwyse.sms.controller.ClassRoomInventoryController.assignBooksToStudents() ");
		Map<Object,Object> responseData = null;
		try {
			responseData = classRoomInventoryService.assignBooksToStudents(tenantId, classRoomBookInventory.getGradeid(), classRoomBookInventory.getClassroom_id());
				if(responseData.get("assigned") !=null) {
					JSONResultEntity<String> response = new JSONResultEntity<String>(true,
							responseData.get("message").toString(), null, Arrays.asList(SMSAPIConstants.CREATE_FAILED));
					return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
				}else {
					String responseMessage = "No assigned books found in the classroom";
					JSONResultEntity<String> response = new JSONResultEntity<String>(false,
							responseData.get("message").toString(), null,
							Arrays.asList());
					return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.BAD_REQUEST);
				}
		} catch (Exception e) {
			LOGGER.debug("Exception occured :  " + e.getMessage());
			JSONResultEntity<String> response = new JSONResultEntity<String>(false, e.getMessage(), null,
					Arrays.asList(SMSAPIConstants.FOUND_FAILED));
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/booksassigned/student/{gradeId}/{classroomId}/{status}")
	public ResponseEntity<?> getAssignedBooksToStudents(@PathVariable("tenantId") long tenantId,
			@PathVariable("gradeId") long gradeId, @PathVariable("classroomId") long classroomId,
			@PathVariable("status") long status) {
		LOGGER.debug("Start : com.omniwyse.sms.controller.ClassRoomInventoryController.getAssignedBooksToStudents() ");
		List<BooksStudentAssignOut> classroomBooks = null;
		try {
			classroomBooks = classRoomInventoryService.getAssignedBooksToStudents(tenantId, gradeId, classroomId,status);
			if (classroomBooks != null) {
				JSONResultEntity<BooksStudentAssignOut> response = new JSONResultEntity<BooksStudentAssignOut>(true,
						SMSAPIConstants.FOUND_SUCCESSFUL, null, classroomBooks);
				return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
			} else {
				String responseMessage = "No assigned books found in the classroom";
				JSONResultEntity<String> response = new JSONResultEntity<String>(false, responseMessage, null,
						Arrays.asList(responseMessage));
				return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.debug("Exception occured :  " + e.getMessage());
			JSONResultEntity<String> response = new JSONResultEntity<String>(false, e.getMessage(), null,
					Arrays.asList(SMSAPIConstants.FOUND_FAILED));
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PreAuthorize("isAuthenticated()")
	@PatchMapping(value = "/classroomInventory/updateBooks")
	public ResponseEntity<?> updateBooksReturnedStatus(@PathVariable("tenantId") long tenantId,@RequestBody ClassRoomBookInventory classRoomBookInventory) {
		LOGGER.debug("Start : com.omniwyse.sms.controller.ClassRoomInventoryController.updateBooksReturnedStatus() ");
		Map<Object,Object> responseData = null;
		try {
			responseData = classRoomInventoryService.updateBooksReturnedStatus(tenantId, classRoomBookInventory);
				if(responseData.get("success") !=null) {
					JSONResultEntity<String> response = new JSONResultEntity<String>(true,
							responseData.get("success").toString(), null, Arrays.asList(SMSAPIConstants.CREATE_FAILED));
					return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.OK);
				}else {
					String responseMessage = "No assigned books found in the classroom";
					JSONResultEntity<String> response = new JSONResultEntity<String>(false,
							"Error occured while updating the data..".toString(), null,
							Arrays.asList(responseMessage));
					return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.BAD_REQUEST);
				}
		} catch (Exception e) {
			LOGGER.info("Exception occured :  " + e.getMessage());
			JSONResultEntity<String> response = new JSONResultEntity<String>(false, e.getMessage(), null,
					Arrays.asList(SMSAPIConstants.FOUND_FAILED));
			return new ResponseEntity<JSONResultEntity<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
