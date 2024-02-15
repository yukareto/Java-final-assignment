CREATE TABLE superGt (
  id INT UNSIGNED AUTO_INCREMENT,
  driver VARCHAR(30) NOT NULL,
  affiliated_team VARCHAR(30) NOT NULL,
  car_number INT NOT NULL,
  PRIMARY KEY(id),
  UNIQUE(driver),
  UNIQUE(car_number),
  UNIQUE(affiliated_team)
);

INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('千代勝正 ' , ' Niterra MOTUL Z', 3);
INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('大湯都史樹', 'ARTA MUGEN NSX-GT', 8);
INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('立川裕路', 'ZENT CERUMO GR Supra', 38);
INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('蒲生尚弥', 'LEON PYRAMID AMG', 65);
INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('井口卓人', 'SUBARU BRZ R&D SPORT', 61);
INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('荒聖治', 'Studie BMW M4', 7);
INSERT INTO superGt (driver, affiliated_team, car_number) VALUES ('谷口信輝', 'GOODSMILE RACING & TeamUKYO', 4);
