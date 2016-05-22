drop table BOOK_AUTHOR;

CREATE TABLE BOOK_AUTHOR
(
   id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   book_id int NOT NULL,
   first_name varchar(50) NOT NULL,
   last_name varchar(50) NOT NULL,
   FOREIGN KEY (book_id) REFERENCES BOOK(id)
);

CREATE UNIQUE INDEX BOOK_01 ON BOOK_AUTHOR(id);
ALTER TABLE BOOK_AUTHOR ADD CONSTRAINT author_unique UNIQUE (book_id, first_name, last_name);