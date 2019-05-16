-- ********** to maintain the individual book information ***********
CREATE TABLE booksinfo(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  title varchar(15) NOT NULL ,
  author varchar(150) NOT NULL,
  isbncode varchar(150) NOT NULL,
  generes varchar(150) NOT NULL,
  edition varchar(150) NOT NULL,
  year date NOT NULL,
  bookcoverpage varchar(150) NOT NULL,
  samplepageurl varchar(150) NOT NULL,
  imagesUrl1 varchar(150) NOT NULL,
  imagesUrl2 varchar(150) NOT NULL,  
  bookcondition varchar(150) NOT NULL,
  bookprice varchar(150) NOT NULL,
  baserentalvalue varchar(150) NOT NULL,
  bookdescription varchar(150) NOT NULL,
  demand int NOT NULL,
  numberofpages int NOT NULL,
  productgroup int NOT NULL,
  publicationdate date,
  publisher varchar(150) NOT NULL,
  createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO booksinfo(title, author, isbncode, generes, edition, year, bookcoverpage,samplepageurl, imagesUrl1, imagesUrl2, bookcondition, bookprice,baserentalvalue,bookdescription, demand, numberofpages,productgroup,publicationdate,publisher) VALUES 
('Head First Java', 'Kathy Sierra', 'ISBN2019', 'Java', '2nd', '1991-09-22', 'simple', 'url', 'Url1', 'Url2', 'Good', '500', '200', 'excellent for programmers', 1, 490, 500, '2000-04-22','JSE');

-- ********** to maintain the all books information ***********
create table books_inventory(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  bookid bigint(8) NOT NULL,
  ownerid bigint(8) NOT NULL,
  available boolean,
  availableDate date,
  bookValue decimal(10,2) NOT NULL,
  rentalValue bigint(8) NOT NULL,
  buyflag bigint(8) NOT NULL,
  rentflag boolean, 
  whereisthebook varchar(150) NOT NULL, 
  locationid bigint(8) NOT NULL,
  createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  FOREIGN KEY (bookid) REFERENCES booksinfo(id),
  FOREIGN KEY (ownerid) REFERENCES classrooms(id),
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

create table recommended_classifiers(
 recommend_id bigint(8) NOT NULL AUTO_INCREMENT,
 recommend_criteria varchar(150) NOT NULL,
 recommenType varchar(150) NOT NULL,
 DisplayText varchar(150) NOT NULL,
 displayed_web boolean,
 order_of_display varchar(150) NOT NULL,
 createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
 modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
 PRIMARY KEY (recommend_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
INSERT INTO recommended_classifiers (recommend_criteria, recommenType, DisplayText, displayed_web, order_of_display) VALUES ('recommended for grade1', '1st class', 'nice', 1, '1');
INSERT INTO recommended_classifiers (recommend_criteria, recommenType, DisplayText, displayed_web, order_of_display) VALUES ('recommended for grade2', '2nd class', 'nice', 1, '2');

create table books_grade_recommendations(
 id bigint(8) NOT NULL AUTO_INCREMENT,
 recommendation_id bigint(8) NOT NULL, 
 bookid bigint(8) NOT NULL, 
 gradeid bigint(8) NOT NULL, 
 rating varchar(150) NOT NULL,
 createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
 modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
 FOREIGN KEY (recommendation_id) REFERENCES recommended_classifiers(recommend_id),
 FOREIGN KEY (bookid) REFERENCES booksinfo(id),
 FOREIGN KEY (gradeid) REFERENCES grades(id),
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

create table books_classroom_inventory(
 id bigint(8) NOT NULL AUTO_INCREMENT,
 bookid bigint(8) NOT NULL, 
 gradeid bigint(8) NOT NULL,
 classroom_id bigint(8) NOT NULL, 
 total_stock decimal(10,2) , 
 avl_stock decimal(10,2) ,
 createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
 modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
 FOREIGN KEY (bookid) REFERENCES booksinfo(id),
 FOREIGN KEY (gradeid) REFERENCES grades(id),
 FOREIGN KEY (classroom_id) REFERENCES classrooms(id),
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--insert into books_classroom_inventory(bookid, classroom_id, total_stock, avl_stock) values(1,1,140,240);

create table books_student_assign(
 id bigint(8) NOT NULL AUTO_INCREMENT,
 bookid bigint(8) NOT NULL, 
 sid  bigint(8) NOT NULL,  
 gradeid bigint(8) NOT NULL, 
 classroom_id  bigint(8) NOT NULL,  
 assigned_date date NOT NULL,
 returned_date date ,
 expected_returned_date date NOT NULL,
 book_status varchar(150),
 fee decimal(10,2), 
 notes varchar(150),
 createdon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
 modifiedon timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
 FOREIGN KEY (bookid) REFERENCES booksinfo(id),
 FOREIGN KEY (sid) REFERENCES students(id),
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

