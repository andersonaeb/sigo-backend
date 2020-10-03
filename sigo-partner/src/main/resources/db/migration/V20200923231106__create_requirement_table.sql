CREATE TABLE requirement (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(200) NOT NULL,
  status varchar(50) NOT NULL,
  description text,
  standard_code varchar(50),
  validity DATE,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;