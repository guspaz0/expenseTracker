create table users(
	id int primary key auto_increment,
	name varchar(50) not null,
	birthdate date
);

create table category(
	id int primary key auto_increment,
	name varchar(50) not null
);

create table expenses(
	id int primary key auto_increment,
	name varchar(50) not null,
	amount decimal(10,2) default 0,
	emit_date date default (CURRENT_DATE) not null
);

create table user_expenses(
	id_user int not null,
	id_expense int not null,
	foreign key(id_expense) references expenses(id),
	foreign key(id_user) references users(id)
);

create table expense_category(
	id_category int not null,
	id_expense int not null,
	foreign key(id_expense) references expenses(id),
	foreign key(id_category) references category(id)
);