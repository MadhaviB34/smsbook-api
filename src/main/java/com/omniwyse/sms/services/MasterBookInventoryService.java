package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.utils.MasterBookInventoryOut;

@Service
public class MasterBookInventoryService {
	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;
   
	private Database db;

	public List<MasterBookInventoryOut> getInventoryBooksByClassroomIdAndSection(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		String query = "select b.title, bi.bookValue, bi.rentalValue, bi.available, bi.whereisthebook, c.gradeid,c.sectionname,c.id  from books_inventory bi " 
				+" LEFT JOIN booksinfo b ON bi.bookid = b.id "
				+ "LEFT JOIN classrooms c ON bi.ownerid = c.id " 
				+ "LEFT JOIN grades g ON c.gradeid = g.gradenumber ";
		return db.sql(query).results(MasterBookInventoryOut.class);
	}

}

