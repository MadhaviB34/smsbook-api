package com.omniwyse.sms.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.BooksRecommendation;
import com.omniwyse.sms.utils.BooksGradeRecommendationOut;
import com.omniwyse.sms.utils.BooksRecommendationDTO;

@Service
public class BooksRecommendationService {
	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;
   
	private Database db;
	
	public BooksRecommendation createBookRec(long tenantId, BooksRecommendationDTO booksRecDTO) {
		db = retrieve.getDatabase(tenantId);		
		BooksRecommendation booksRec = new BooksRecommendation();	
		booksRec.setRecommendation_id(booksRecDTO.getRecommendation_id());
		booksRec.setBookid(booksRecDTO.getBookid());
		booksRec.setGradeid(booksRecDTO.getGradeid());
		//booksRec.setClassroomid(booksRecDTO.getClassroomid());
		booksRec.setRating(booksRecDTO.getRating());
		booksRec.setCreatedon(new Date());
		booksRec.setModifiedon(new Date());
		db.insert(booksRec).getRowsAffected();
		return booksRec;
	}
	public BooksRecommendation updateBookRecById(long tenantId,long booksRecId, BooksRecommendationDTO booksRecDTO) {
		db = retrieve.getDatabase(tenantId);
		BooksRecommendation booksRec  = db.where("id=?", booksRecId).results(BooksRecommendation.class).get(0);
		booksRec.setRecommendation_id(booksRecDTO.getRecommendation_id());
		booksRec.setBookid(booksRecDTO.getBookid());
		booksRec.setGradeid(booksRecDTO.getGradeid());
		booksRec.setClassroomid(booksRecDTO.getClassroomid());
		booksRec.setRating(booksRecDTO.getRating());
		db.update(booksRec).getRowsAffected();
		return booksRec;
	}
	
	public List<BooksGradeRecommendationOut> getBooksGradeRecommendationList(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		String query = "select b.title, g.gradename, bgr.id, bgr.createdon, bgr.bookid, bgr.gradeid from books_grade_recommendations bgr  " 
				+" LEFT JOIN booksinfo b ON bgr.bookid = b.id  "
				+ "LEFT JOIN grades g ON bgr.gradeid = g.gradenumber ";
		return db.sql(query).results(BooksGradeRecommendationOut.class);
	}

}
