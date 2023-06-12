DROP TABLE IF EXISTS tb_clients;
CREATE TABLE tb_clients (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sur_name VARCHAR(255),
    type VARCHAR(255),
    public_place VARCHAR(255),
    number VARCHAR(255),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    zip_code VARCHAR(255),
    city_name VARCHAR(255),
    state_tag VARCHAR(2),
    country_name VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    register VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    description TEXT,
    CONSTRAINT UQ_clients_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
