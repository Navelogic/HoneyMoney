INSERT INTO tb_categorys (name) VALUES ('Eletrônicos');
INSERT INTO tb_categorys (name) VALUES ('Roupas');
INSERT INTO tb_categorys (name) VALUES ('Alimentos');
INSERT INTO tb_categorys (name) VALUES ('Móveis');
INSERT INTO tb_categorys (name) VALUES ('Livros');

INSERT INTO tb_clients (sur_name, type, public_place, number, complement, neighborhood, zip_code, city_name, state_tag, country_name, name, email, register, phone, active, description)
VALUES ('Sobrenome 1', 'Tipo 1', 'Rua 1', '123', 'Complemento 1', 'Bairro 1', '12345-678', 'Cidade 1', 'ST', 'País 1', 'Nome 1', 'email1@example.com', '123456789', '987654321', true, 'Descrição 1');

INSERT INTO tb_clients (name, email, register, phone, active)
VALUES ('Nome 2', 'email2@example.com', '987654321', '123456789', false);

INSERT INTO tb_clients (name, email, register, phone, active, complement, description)
VALUES ('Nome 3', 'email3@example.com', '123456789', '987654321', true, '', NULL);

INSERT INTO tb_clients (name, email, register, phone, active, description)
VALUES ('Nome 4', 'email4@example.com', '987654321', '123456789', true, 'Descrição 4: Lorem ipsum dolor sit amet.');

INSERT INTO tb_clients (name, email, register, phone, active, type, complement, description)
VALUES ('Nome 5', 'email5@example.com', '123456789', '987654321', true, '', '', '');

INSERT INTO tb_piches (description, date_expiration, date_payment, price, type, id_category, id_client)
VALUES ('Compra de computadores', '2023-07-31', '2023-07-15', 50.00, 'RECEITA', 1, 1);

INSERT INTO tb_piches (description, date_expiration, date_payment, price, type, id_category, id_client)
VALUES ('Pichê 2', '2023-08-31', NULL, 80.00, 'RECEITA', 2, 2);

INSERT INTO tb_piches (description, date_expiration, date_payment, price, type, id_category, id_client)
VALUES ('Pichê 3', '2023-09-30', '2023-09-15', 120.00, 'RECEITA', 1, 3);

INSERT INTO tb_piches (description, date_expiration, date_payment, price, type, id_category, id_client)
VALUES ('Pichê 4', '2023-10-31', NULL, 90.00, 'RECEITA', 2, 4);

INSERT INTO tb_piches (description, date_expiration, date_payment, price, type, id_category, id_client)
VALUES ('Pichê 5', '2023-11-30', '2023-11-15', 150.00, 'RECEITA', 1, 5);




