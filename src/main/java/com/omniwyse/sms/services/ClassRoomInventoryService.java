package com.omniwyse.sms.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.BooksStudentAssign;
import com.omniwyse.sms.utils.BooksStudentAssignOut;
import com.omniwyse.sms.utils.ClassRoomBookInventory;
import com.omniwyse.sms.utils.ClassRoomStudents;

@Service
public class ClassRoomInventoryService {

	public static final String GET_ASSIGNED_BOOKS_CLASSROOM = "select b.title, g.gradename, c.sectionname, bci.id, bci.gradeid, bci.createdon, bci.bookid, bci.classroom_id,b.title,b.bookprice,b.baserentalvalue from books_classroom_inventory bci inner JOIN booksinfo b ON bci.bookid = b.id inner JOIN classrooms c ON bci.classroom_id = c.id and bci.classroom_id = ? inner JOIN grades g ON bci.gradeid = g.gradenumber and bci.gradeid = ? ";
	public static final String GET_ASSIGNED_BOOKS_TO_STUDENTS = "select b.title, s.name, bsa.assigned_date, bsa.expected_returned_date, bsa.returned_date, bsa.book_status, bsa.fee, bsa.notes,bsa.bookid,bsa.sid,bsa.id,bsa.gradeid,bsa.classroom_id from books_student_assign bsa LEFT JOIN booksinfo b ON bsa.bookid = b.id LEFT JOIN students s ON bsa.sid = s.id where bsa.gradeid=? and bsa.classroom_id=?";
	public static final String RETURNED_BOOKS = " AND BSA.RETURNED_DATE IS NOT NULL";
	public static final String NOT_RETURNED_BOOKS = " AND BSA.RETURNED_DATE IS NULL";
	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;

	@Autowired
	private StudentsService service;

	@Autowired
	private BooksStudentAssignService booksStudentAssignService;

	private Database db;

	public List<ClassRoomBookInventory> getAssignedBooksByClassroom(long tenantId, long gradeId, long classroomId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql(GET_ASSIGNED_BOOKS_CLASSROOM, classroomId, gradeId).results(ClassRoomBookInventory.class);
	}

	public Map<Object, Object> assignBooksToStudents(long tenantId, long gradeId, long classroomId) {
		Map<Object, Object> response = new HashMap<>();
		db = retrieve.getDatabase(tenantId);
		List<ClassRoomStudents> listOfStudents = service.getStudentsOfClassRoom(classroomId, tenantId);
		List<BooksStudentAssignOut> booksStudentAssignList = booksStudentAssignService.viewAssignedBooks(tenantId);
		List<ClassRoomBookInventory> classRoomInventoryBooks = db
				.sql(GET_ASSIGNED_BOOKS_CLASSROOM, classroomId, gradeId).results(ClassRoomBookInventory.class);
		List<BooksStudentAssign> assignedBooksToStudents = new ArrayList<>();
		List<Long> listOfStudentsEligible = new ArrayList<>();
		List<Long> listOfStudentsIds = new ArrayList<>();
		Map<Long, Long> booksCount = new HashMap<>();
		for (ClassRoomBookInventory classRoomBook : classRoomInventoryBooks) {
			booksCount.put(classRoomBook.getBookid(), classRoomBook.getCount());
		}
		for(ClassRoomStudents student : listOfStudents) {
			listOfStudentsIds.add(student.getId());
		}
		for (BooksStudentAssignOut assignedBook : booksStudentAssignList) {
			if (assignedBook.getReturned_date() != null && !assignedBook.getReturned_date().equals("")
					&& !listOfStudentsEligible.contains(assignedBook.getSid())) {
				listOfStudentsEligible.add(assignedBook.getSid());
			}else if((assignedBook.getReturned_date() == null || assignedBook.getReturned_date().equals(""))
					&& listOfStudentsEligible.contains(assignedBook.getSid())) {
				listOfStudentsEligible.remove(assignedBook.getSid());
			}
			if(listOfStudentsIds.contains(assignedBook.getSid())) {
				listOfStudentsIds.remove(assignedBook.getSid());
			}
		}
		if(listOfStudentsEligible.size() == 0 && listOfStudentsIds.size() == 0) {
			response.put("message", "No eligible students found to assign the books");
			return response;
		}
		
		BooksStudentAssign booksStudentAssign = null;
		for (ClassRoomStudents student : listOfStudents) {
			if (listOfStudentsEligible.contains(student.getId()) || listOfStudentsIds.contains(student.getId())) {
				for (ClassRoomBookInventory classRoomBook : classRoomInventoryBooks) {
					List<Long> assignedBooks = new ArrayList<>();
					for (BooksStudentAssignOut assignedBook : booksStudentAssignList) {
						if (student.getId() == assignedBook.getSid()) {
							assignedBooks.add(assignedBook.getBookid());
						}
					}
					if (!assignedBooks.contains(classRoomBook.getBookid())
							&& booksCount.get(classRoomBook.getBookid()) > 0) {
						booksStudentAssign = new BooksStudentAssign();
						booksStudentAssign.setBookid(classRoomBook.getBookid());
						booksStudentAssign.setSid(student.getId());
						booksStudentAssign.setAssigned_date(new Date());
						booksStudentAssign.setGradeid(gradeId);
						booksStudentAssign.setClassroom_id(classroomId);
						Date currentDate = new Date();
						Calendar calender = Calendar.getInstance();
						calender.setTime(currentDate);
						calender.add(Calendar.DATE, 7); // same with c.add(Calendar.DAY_OF_MONTH, 1);
						Date currentDatePlusSeven = calender.getTime();
						booksStudentAssign.setExpected_returned_date(currentDatePlusSeven);
						booksStudentAssign.setCreatedon(new Date());
						booksStudentAssign.setModifiedon(new Date());
						assignedBooksToStudents.add(booksStudentAssign);
						booksCount.put(classRoomBook.getBookid(), booksCount.get(classRoomBook.getBookid()) - 1);
						break;
					}
				}
			}
		}
		// if(assignedBooksToStudents.size() == listOfStudents.size()) {
		if(assignedBooksToStudents.size() > 0) {
		for (BooksStudentAssign bookAssign : assignedBooksToStudents) {
			/// Save here
			db.insert(bookAssign).getRowsAffected();
		}
		response.put("assigned", true);
		response.put("message", "Successfully assigned books to students");
		}else {
			response.put("message", "No eligible students found to assign the books");
		}
		// }
		/*
		 * else { response.put("errorMessage",
		 * "Number of Books are not sufficient for number of students"); }
		 */
		return response;
	}

	public List<BooksStudentAssignOut> getAssignedBooksToStudents(long tenantId, long gradeId, long classroomId,
			long status) {
		String query = GET_ASSIGNED_BOOKS_TO_STUDENTS;
		if (status == 1) {
			query += NOT_RETURNED_BOOKS;
		} else if (status == 2) {
			query += RETURNED_BOOKS;
		}
		db = retrieve.getDatabase(tenantId);
		return db.sql(query, gradeId, classroomId).results(BooksStudentAssignOut.class);
	}

	public Map<Object, Object> updateBooksReturnedStatus(long tenantId, ClassRoomBookInventory classRoomBookInventory) {
		Map<Object, Object> response = new HashMap<>();
		if (classRoomBookInventory != null && classRoomBookInventory.getListOfAssignedBooks() != null) {
			for (BooksStudentAssign book : classRoomBookInventory.getListOfAssignedBooks()) {
				if(book.getReturned_date() != null && !book.getReturned_date().equals("")) {
					book.setCreatedon(new Date());
					book.setModifiedon(new Date());
					db.update(book).getRowsAffected();
				}
			}
		}
		response.put("success", "Books return details updated successfully ... ");
		return response;
	}

}
