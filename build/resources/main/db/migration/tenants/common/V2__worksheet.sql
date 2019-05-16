
--******************** WORKSHEETS TABLES **********************************************************************************************************

CREATE TABLE degreeofdifficulty(
	id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	degreeofdifficulty VARCHAR(50) NOT NULL
);

INSERT INTO degreeofdifficulty (degreeofdifficulty) values('EASY'),('INTERMEDIATE'),('ADVANCED');

CREATE TABLE worksheets(
	id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	worksheetname VARCHAR(50) NOT NULL,
	gradeid bigint NOT NULL,
	subjectid bigint NOT NULL,
	degreeofdifficultyid bigint NOT NULL,
	tags VARCHAR(100) NOT NULL,
	description VARCHAR(100) NOT NULL,
	worksheetpath VARCHAR(100) NOT NULL,
	worksheet blob,
	createdby VARCHAR(50),
	createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
	FOREIGN KEY(gradeid) REFERENCES grades(id),
	FOREIGN KEY(subjectid) REFERENCES grade_subjects(id),
	FOREIGN KEY(degreeofdifficultyid) REFERENCES degreeofdifficulty (id)
); 
