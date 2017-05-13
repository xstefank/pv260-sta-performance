-- df statuscodes: word=C:/tmp/dict/file-with-status-codes
-- df names: word=C:/tmp/dict/names.txt
-- df booktitles: word=C:/tmp/dict/booktitles.txt
-- df words: word=C:/tmp/dict/words.txt


CREATE TABLE Status ( -- df: nogen size=3
  id      int NOT NULL, 
  code varchar(20), 
  PRIMARY KEY (id));

CREATE TABLE Address ( -- df: mult=0.8
  id      int NOT NULL, 
  street varchar(10) NOT NULL, 
  number int NOT NULL, 
  city   varchar(10) NOT NULL, 
  zip    int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE Reader ( -- df: mult=1.0
  id     int NOT NULL, 
  name  varchar(70), -- df: text=names length=2 lenvar=1
  birth DATE NOT NULL, -- df: start=1923-01-01 end=2010-01-01
  email varchar(40), -- df: pattern='[a-z]{3,8}\.[a-z]{3,8}@(gmail|hotmail)\.com'
  phone varchar(10), -- df: chars='0-9' length=10 lenvar=0  -- df: null=0.01 size=1000000
  PRIMARY KEY (id));

CREATE TABLE Book ( -- df: mult=1.2
  id      int NOT NULL, 
  author varchar(70) NOT NULL, -- df: text=names length=2 lenvar=1
  title  varchar(150) NOT NULL, -- df: text=words length=4 lenvar=3
  published   DATE NOT NULL, -- df: start=1900-01-01 end=2015-01-01
  isbn   varchar(15) NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE BookItem ( -- df: mult=1.7
  id           int NOT NULL, 
  Book_id     int NOT NULL REFERENCES Book, 
  Status_code int NOT NULL REFERENCES Status, -- df: int sub=scale rate=0.95
  PRIMARY KEY (id));

CREATE TABLE Address_Reader (
  Address_id int NOT NULL REFERENCES Address, 
  Reader_id  int NOT NULL REFERENCES Reader, 
  PRIMARY KEY (Address_id, 
  Reader_id));

CREATE TABLE Loan ( -- df: mult=0.7
  id           int NOT NULL, 
  loaned      timestamp NOT NULL, -- df: size=100000 prec=60
  returned        timestamp,  -- df: null=0.4
  BookItem_id int NOT NULL REFERENCES BookItem, 
  Reader_id   int NOT NULL REFERENCES Reader, 
  PRIMARY KEY (id));