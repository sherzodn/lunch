CREATE DATABASE voting CHARACTER SET UTF8;
CREATE USER voting@localhost IDENTIFIED BY 'sherzodn';
GRANT ALL PRIVILEGES ON voting.* TO voting@localhost;
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'voting'@'localhost';