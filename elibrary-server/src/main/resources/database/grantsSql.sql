--the root user will have full access to this database
GRANT ALL ON elibrary.* TO 'root'@'localhost';

--the lib_user rights
GRANT SELECT, INSERT, UPDATE, DELETE ON elibrary.* TO 'lib_user'@'localhost';