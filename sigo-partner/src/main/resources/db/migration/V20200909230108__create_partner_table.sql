CREATE TABLE partner (
  id int NOT NULL AUTO_INCREMENT,
  company_name varchar(100) NOT NULL,
  cnpj varchar(14) NOT NULL,
  state varchar(50) DEFAULT NULL,
  city varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;