package com.omniwyse.sms.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.BooksStudentAssign;
import com.omniwyse.sms.utils.BooksStudentAssignDTO;
import com.omniwyse.sms.utils.BooksStudentAssignOut;

@Service
public class BooksStudentAssignService {
	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;
   
	private Database db;
	public static final String GET_ASSIGNED_BOOKS_TO_STUDENTS="select b.title, s.name, bsa.assigned_date, bsa.expected_returned_date, bsa.returned_date, bsa.book_status, bsa.fee, bsa.notes,bsa.bookid,bsa.sid from books_student_assign bsa LEFT JOIN booksinfo b ON bsa.bookid = b.id LEFT JOIN students s ON bsa.sid = s.id";
	 
	public BooksStudentAssign createBooksStudentAssign(long tenantId, BooksStudentAssignDTO booksStudentAssignDTO) {
		db = retrieve.getDatabase(tenantId);
		BooksStudentAssign booksStudentAssign = new BooksStudentAssign();		
		booksStudentAssign.setBookid(booksStudentAssignDTO.getBookid());
		booksStudentAssign.setSid(booksStudentAssignDTO.getSid());
		booksStudentAssign.setAssigned_date(booksStudentAssignDTO.getAssigned_date());
		booksStudentAssign.setReturned_date(booksStudentAssignDTO.getReturned_date());
		booksStudentAssign.setExpected_returned_date(booksStudentAssignDTO.getExpected_returned_date());
		booksStudentAssign.setBook_status(booksStudentAssignDTO.getBook_status());
		booksStudentAssign.setFee(booksStudentAssignDTO.getFee());
		booksStudentAssign.setNotes(booksStudentAssignDTO.getNotes());
		booksStudentAssign.setCreatedon(new Date());
		booksStudentAssign.setModifiedon(new Date());
		db.insert(booksStudentAssign).getRowsAffected();
		return booksStudentAssign;
	}
	
	public BooksStudentAssign updateStudentBookAssignById(long tenantId, long studentBookId, BooksStudentAssignDTO booksStudentAssignDTO) {
		db = retrieve.getDatabase(tenantId);		
		BooksStudentAssign booksStudentAssign = db.where("id=?", studentBookId).results(BooksStudentAssign.class).get(0);	
		booksStudentAssign.setBookid(booksStudentAssignDTO.getBookid());
		booksStudentAssign.setSid(booksStudentAssignDTO.getSid());
		booksStudentAssign.setAssigned_date(booksStudentAssignDTO.getAssigned_date());
		booksStudentAssign.setReturned_date(booksStudentAssignDTO.getReturned_date());
		booksStudentAssign.setExpected_returned_date(booksStudentAssignDTO.getExpected_returned_date());
		booksStudentAssign.setBook_status(booksStudentAssignDTO.getBook_status());
		booksStudentAssign.setFee(booksStudentAssignDTO.getFee());
		booksStudentAssign.setNotes(booksStudentAssignDTO.getNotes());
		db.update(booksStudentAssign).getRowsAffected();
		return booksStudentAssign;
	}
			
	public List<BooksStudentAssignOut> viewAssignedBooks(long tenantId) {
	   db = retrieve.getDatabase(tenantId);
	   /*String query = "select b.title, s.name, bsa.assigned_date, bsa.returned_date, bsa.expected_returned_date, bsa.book_status, bsa.fee, bsa.notes from books_student_assign bsa " 
				+" LEFT JOIN booksinfo b ON bsa.bookid = b.id "
				+" LEFT JOIN students s ON bsa.sid = s.id " ;*/
		return db.sql(GET_ASSIGNED_BOOKS_TO_STUDENTS).results(BooksStudentAssignOut.class);
	}

}
