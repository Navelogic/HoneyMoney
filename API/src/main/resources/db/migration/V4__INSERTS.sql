INSERT INTO tb_categorys (name) VALUES ('Eletrônicos');
INSERT INTO tb_categorys (name) VALUES ('Roupas');
INSERT INTO tb_categorys (name) VALUES ('Alimentos');
INSERT INTO tb_categorys (name) VALUES ('Móveis');
INSERT INTO tb_categorys (name) VALUES ('Livros');

INSERT INTO tb_clients (name, email, register, phone, sur_name, type, public_place, number, complement, neighborhood, zip_code, city_name, state_tag, country_name)
VALUES ('João da Silva', 'joao.silva@gmail.com', '12345678900', '987654321', 'Silva', 'Casa', 'Rua A', '123', 'Apartamento 1', 'Centro', '12345-678', 'São Paulo', 'SP', 'Brasil');

INSERT INTO tb_clients (name, email, register, phone, sur_name, type, public_place, number, complement, neighborhood, zip_code, city_name, state_tag, country_name)
VALUES ('Maria Souza', 'maria.souza@gmail.com', '98765432100', '123456789', 'Souza', 'Apartamento', 'Av. B', '456', 'Bloco C', 'Bela Vista', '98765-432', 'Rio de Janeiro', 'RJ', 'Brasil');

INSERT INTO tb_clients (name, email, register, phone, sur_name, type, public_place, number, complement, neighborhood, zip_code, city_name, state_tag, country_name)
VALUES ('Pedro Santos', 'pedro.santos@gmail.com', '55555555555', '999999999', 'Santos', 'Casa', 'Rua C', '789', 'Casa', 'Jardim das Flores', '54321-987', 'Belo Horizonte', 'MG', 'Brasil');

INSERT INTO tb_clients (name, email, register, phone, sur_name, type, public_place, number, complement, neighborhood, zip_code, city_name, state_tag, country_name)
VALUES ('Ana Oliveira', 'ana.oliveira@gmail.com', '88888888888', '777777777', 'Oliveira', 'Apartamento', 'Av. D', '987', 'Apartamento 2', 'Centro', '76543-210', 'Salvador', 'BA', 'Brasil');

INSERT INTO tb_clients (name, email, register, phone, sur_name, type, public_place, number, complement, neighborhood, zip_code, city_name, state_tag, country_name)
VALUES ('José Costa', 'jose.costa@gmail.com', '44444444444', '666666666', 'Costa', 'Casa', 'Rua E', '321', 'Casa', 'Vila Nova', '21098-765', 'Porto Alegre', 'RS', 'Brasil');

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




