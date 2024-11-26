--DROP TABLE IF EXISTS EXPIRATION_PAYMENTS;
--DROP TABLE IF EXISTS PAYMENTS;
--DROP TABLE IF EXISTS EXPIRATIONS;
--DROP TABLE IF EXISTS expenses;


--DROP TABLE IF EXISTS users;
create TABLE IF NOT exists users(
	id identity primary key,
	name varchar(50) not null,
	email varchar(50) NOT NULL,
	password varchar(255) NOT null
);

--DROP TABLE IF EXISTS category;
create TABLE IF NOT exists category(
	id identity primary key,
	name varchar(50) not NULL,
	description varchar(128)
);

--DROP TABLE IF EXISTS SUPPLIERS;
CREATE TABLE IF NOT EXISTS SUPPLIERS(
	id identity PRIMARY KEY,
	name varchar(50) NOT NULL
);


CREATE TABLE IF NOT exists payments(
	id identity PRIMARY KEY,
	date date default(CURRENT_DATE),
	amount double NOT NULL,
	id_suplier int NOT NULL,
	id_user int NOT NULL,
	FOREIGN key(id_suplier) REFERENCES suppliers(id),
	FOREIGN key(id_user) REFERENCES users(id),
	check(amount > 0)
);

create TABLE IF NOT exists expenses(
	id identity primary key,
	description varchar(128) not null,
	amount decimal(10,2),
	EMIT_DATE date NOT NULL DEFAULT(CURRENT_DATE),
	id_supplier int NOT NULL,
	id_user int NOT NULL,
	id_category int NOT NULL,
	expires int DEFAULT 0,
	FOREIGN KEY(id_supplier) REFERENCES SUPPLIERS(id),
	FOREIGN KEY(id_user) REFERENCES USERS(id),
	FOREIGN key(id_category) references category(id),
	check(expires = 1 OR expires = 0)
);


CREATE TABLE IF NOT EXISTS EXPIRATIONS(
	id identity  PRIMARY KEY,
	id_expense int NOT null,
	expiration date default(CURRENT_DATE) NOT NULL,
	participation decimal(4,3) DEFAULT 1,
	FOREIGN KEY(id_expense) REFERENCES expenses(id),
	CHECK(participation <= 1)
);


CREATE TABLE IF NOT exists EXPIRATION_PAYMENTS(
	id identity PRIMARY KEY,
	part_expiration decimal(4,3) NOT NULL DEFAULT 1,
	part_payment decimal(4,3) NOT NULL DEFAULT 1,
	id_payment int NOT NULL,
	id_expiration int NOT NULL,
	FOREIGN KEY(id_payment) REFERENCES PAYMENTS(id),
	FOREIGN KEY(id_expiration) REFERENCES EXPIRATIONS(id),
	check(part_expiration <= 1 AND part_payment <= 1 AND part_expiration > 0 AND part_payment > 0)
);

--INSERT INTO USERS VALUES
--('1','Gustavo Paz', 'gusti.paz@gmail.com', '1234');
--
--INSERT INTO SUPPLIERS(ID, NAME) VALUES
--('1','EDESE SA'),
--('2','NATURGY SA'),
--('3','TELECOM SA'),
--('4','ALMACEN DEL BARRIO SRL'),
--('5','GRIMOLDI'),
--('6','HENRY');
--
--INSERT INTO CATEGORY VALUES
--('1','Bienes basicos','Productos basicos tangibles como por ejemplo ropa, comida'),
--('2','Servicios basicos','Servicios de basicos por ejemplo agua, electricidad, gas, telefonia, etc.');
--
--INSERT INTO expenses(DESCRIPTION, EMIT_DATE, AMOUNT,ID_USER, ID_SUPPLIER,ID_CATEGORY, EXPIRES) VALUES
--('factura de Electricidad periodo de agosto 2024','2024-10-24',100,1,'1','2',1),
--('factura de Gas periodo de agosto 2024','2024-09-28',150,1,'2','2',0),
--('factura de Telefonia movil periodo de agosto 2024','2024-08-03',300,1,'3','2',0),
--('factura de comida','2024-09-15',1150,1,'4','1',0),
--('factura de ropa','2024-08-26',2300,1,'5','1',0),
--('factura de universidad','2024-10-12',5000,1,'6','2',0);
--
--INSERT INTO EXPIRATIONS(ID_EXPENSE, EXPIRATION, PARTICIPATION) VALUES
--('1','2024-11-30',0.5),
--('1','2024-12-31',0.5);