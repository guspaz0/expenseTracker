DROP TABLE IF EXISTS EXPIRATION_PAYMENTS;
DROP TABLE IF EXISTS PAYMENTS;
DROP TABLE IF EXISTS EXPIRATIONS;
DROP TABLE IF EXISTS expenses;


DROP TABLE IF EXISTS users;
create TABLE IF NOT exists users(
	id identity primary key,
	name varchar(50) not null,
	email varchar(50) NOT NULL,
	password varchar(255) NOT null
);

DROP TABLE IF EXISTS category;
create TABLE IF NOT exists category(
	id identity primary key,
	name varchar(50) not NULL,
	description varchar(128)
);

DROP TABLE IF EXISTS SUPPLIERS;
CREATE TABLE IF NOT EXISTS suppliers(
	id identity PRIMARY KEY,
	name varchar(50) NOT NULL
);


CREATE TABLE IF NOT exists payments(
	id identity PRIMARY KEY,
	date date default(CURRENT_DATE),
	amount double NOT NULL,
	supplier_id int NOT NULL,
	user_id int NOT NULL,
	FOREIGN key(supplier_id) REFERENCES suppliers(id),
	FOREIGN key(user_id) REFERENCES users(id),
	check(amount > 0)
);

create TABLE IF NOT exists expenses(
	id identity primary key,
	description varchar(128) not null,
	amount decimal(10,2),
	emit_date date NOT NULL DEFAULT(CURRENT_DATE),
	supplier_id int NOT NULL,
	user_id int NOT NULL,
	category_id int NOT NULL,
	expires int DEFAULT 0,
	FOREIGN KEY(supplier_id) REFERENCES SUPPLIERS(id),
	FOREIGN KEY(user_id) REFERENCES USERS(id),
	FOREIGN key(category_id) references category(id),
	check(expires = 1 OR expires = 0)
);


CREATE TABLE IF NOT EXISTS expirations(
	id identity PRIMARY KEY,
	expense_id int NOT null,
	expire_date date default(CURRENT_DATE) NOT NULL,
	participation decimal(4,3) DEFAULT 1,
	FOREIGN KEY(expense_id) REFERENCES expenses(id),
	CHECK(participation <= 1)
);


CREATE TABLE IF NOT exists EXPIRATION_PAYMENTS(
	id identity PRIMARY KEY,
	expiration_part decimal(4,3) NOT NULL DEFAULT 1,
	payment_part decimal(4,3) NOT NULL DEFAULT 1,
	payment_id int NOT NULL,
	expiration_id int NOT NULL,
	FOREIGN KEY(payment_id) REFERENCES PAYMENTS(id) ON DELETE cascade,
	FOREIGN KEY(expiration_id) REFERENCES EXPIRATIONS(id),
	check(expiration_part <= 1 AND payment_part <= 1 AND expiration_part > 0 AND payment_part > 0)
);
