package com.omniwyse.sms.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.ClassroomBooks;
import com.omniwyse.sms.utils.ClassroomBooksAssignedOut;
import com.omniwyse.sms.utils.ClassroomBooksDTO;

@Service
public class ClassroomBooksService {
	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;
   
	private Database db;

	public List<ClassroomBooksAssignedOut> getBooksByClassroom(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		String query = "select b.title, g.gradename, c.sectionname, bci.id, bci.gradeid, bci.createdon, bci.bookid, bci.classroom_id from books_classroom_inventory bci"
				     + " LEFT JOIN booksinfo b ON bci.bookid = b.id " 
				     + " LEFT JOIN classrooms c ON bci.classroom_id = c.id "
				     + " LEFT JOIN grades g ON bci.gradeid = g.gradenumber";
		return db.sql(query).results(ClassroomBooksAssignedOut.class);
	}

	public ClassroomBooks createClsrmBooksAssign(long tenantId, ClassroomBooksDTO classroomBooksDTO) {
		db = retrieve.getDatabase(tenantId);		
		ClassroomBooks classroomBooks = new ClassroomBooks();	
		classroomBooks.setBookid(classroomBooksDTO.getBookid());
		classroomBooks.setGradeid(classroomBooksDTO.getGradeid());
		classroomBooks.setClassroom_id(classroomBooksDTO.getClassroom_id());
		classroomBooks.setTotal_stock(classroomBooksDTO.getTotal_stock());
		classroomBooks.setAvl_stock(classroomBooksDTO.getAvl_stock());
		classroomBooks.setCreatedon(new Date());
		classroomBooks.setModifiedon(new Date());
		db.insert(classroomBooks).getRowsAffected();
		return classroomBooks;
	}

	public ClassroomBooks updateClassroomBooksById(long tenantId, long classroomBookId, ClassroomBooksDTO classroomBooksDTO) {
		db = retrieve.getDatabase(tenantId);		
		ClassroomBooks classroomBooks = db.where("id=?", classroomBookId).results(ClassroomBooks.class).get(0);	
		classroomBooks.setBookid(classroomBooksDTO.getBookid());
		classroomBooks.setGradeid(classroomBooksDTO.getGradeid());
		classroomBooks.setClassroom_id(classroomBooksDTO.getClassroom_id());
		classroomBooks.setTotal_stock(classroomBooksDTO.getTotal_stock());
		classroomBooks.setAvl_stock(classroomBooksDTO.getAvl_stock());
		classroomBooks.setCreatedon(new Date());
		classroomBooks.setModifiedon(new Date());
		db.update(classroomBooks).getRowsAffected();
		return classroomBooks;
	}
	
}
