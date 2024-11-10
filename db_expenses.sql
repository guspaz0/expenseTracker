DROP TABLE IF exists USER_expenses;
DROP TABLE IF exists EXPENSE_CATEGORY;

DROP TABLE IF EXISTS users;
create table users(
	id int primary key auto_increment,
	name varchar(50) not null,
	email varchar(50) UNIQUE NOT null
);

DROP TABLE IF EXISTS category;
create table category(
	id int primary key auto_increment,
	name varchar(50) UNIQUE not NULL,
	description varchar(128)
);

drop TABLE IF EXISTS expenses;
create table expenses(
	id int primary key auto_increment,
	description varchar(50) not null,
	amount decimal(10,2) default 0,
	date date default (CURRENT_DATE) not null
);


create table user_expenses(
	id_user int not null,
	id_expense int not null,
	foreign key(id_expense) references expenses(id) ON DELETE cascade,
	foreign key(id_user) references users(id)  ON DELETE cascade
);


create table expense_category(
	id_category int not null,
	id_expense int not null,
	foreign key(id_expense) references expenses(id) ON DELETE cascade,
	foreign key(id_category) references category(id) ON DELETE cascade
);