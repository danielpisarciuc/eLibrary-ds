drop table BOOK_DETAIL;

CREATE TABLE BOOK_DETAIL
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   book_id int NOT NULL,
   language varchar(500) NOT NULL,
   format varchar(500) NOT NULL,
   subject varchar(500) NULL,
   publication_date timestamp,
   description varchar(500) ,
   FOREIGN KEY (book_id) REFERENCES BOOK(id)
);

CREATE UNIQUE INDEX BOOK_01 ON BOOK_DETAIL(id);
ALTER TABLE BOOK_DETAIL ADD CONSTRAINT detail_unique UNIQUE (book_id, language, format);