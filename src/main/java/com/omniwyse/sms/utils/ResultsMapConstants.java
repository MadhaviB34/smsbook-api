package com.omniwyse.sms.utils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.Grades;

public class ResultsMapConstants {
	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrieve;
   
	private Database db;
	
    public Map<Long, String> gradesMap = getGradesMap();
    public Map<Long, String> sectionsMap = getSectionsMap();
    
    public Map<Long, String> getGradesMap(){    
		List<Grades> gradesList= db.sql("select gradenumber, gradename from grades").results(Grades.class);
		for(Grades grades : gradesList) {
			gradesMap.put(grades.getGradenumber(), grades.getGradename());
		}
		return gradesMap;
    }
    public Map<Long, String> getSectionsMap(){
    	db = retrieve.getDatabase(2);
		List<ClassRoom> classroomList= db.sql("select id, sectionname from classrooms").results(ClassRoom.class);
		for(ClassRoom classroom : classroomList) {
			sectionsMap.put(classroom.getId(), classroom.getSectionname());
		}
		return gradesMap;
    }
    
}
