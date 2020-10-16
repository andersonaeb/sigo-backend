CREATE TABLE standard (
  id int NOT NULL AUTO_INCREMENT,
  code varchar(50) NOT NULL,
  title varchar(100) NOT NULL,
  category varchar(50) NOT NULL,
  keywords varchar(250) DEFAULT NULL,
  description TEXT DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
