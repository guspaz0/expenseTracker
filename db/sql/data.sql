INSERT INTO USERS(NAME,EMAIL,PASSWORD, COUNTRY, CURRENCY, USER_ROLE) VALUES
('Jhon Doe', 'Jhon.Doe@gmail.com', '1234', 'AR', 'ARS', 'ROLE_ADMIN'),
('Don Marin', 'don.marin@gmail.com', '1234', 'UY', 'USD', 'ROLE_USER');

INSERT INTO SUPPLIERS(NAME) VALUES
('EDESE SA'),
('NATURGY SA'),
('TELECOM SA'),
('ALMACEN DEL BARRIO SRL'),
('GRIMOLDI'),
('HENRY');

INSERT INTO CATEGORY(NAME, DESCRIPTION) VALUES
('Bienes basicos','Productos basicos tangibles como por ejemplo ropa, comida'),
('Servicios basicos','Servicios de basicos por ejemplo agua, electricidad, gas, telefonia, etc.');

INSERT INTO expenses(DESCRIPTION, EMIT_DATE, AMOUNT, CURRENCY, USER_ID, SUPPLIER_ID,CATEGORY_ID, EXPIRES) VALUES
('factura de Electricidad periodo de agosto 2024','2024-10-24',100,'ARS',1,'1','2',1),
('factura de Gas periodo de agosto 2024','2024-09-28',150,'ARS',1,'2','2',0),
('factura de Telefonia movil periodo de agosto 2024','2024-08-03',300,'ARS',1,'3','2',0),
('factura de comida','2024-09-15',1150,'ARS',1,'4','1',0),
('factura de ropa','2024-08-26',2300,'ARS',1,'5','1',0),
('factura de universidad','2024-10-12',5000,'ARS',1,'6','2',0);

INSERT INTO EXPIRATIONS(EXPENSE_ID, EXPIRE_DATE, PARTICIPATION) VALUES
('1','2024-11-30',0.5),
('1','2024-12-31',0.5);