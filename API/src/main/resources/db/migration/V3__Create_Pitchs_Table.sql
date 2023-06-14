DROP TABLE IF EXISTS tb_piches;
CREATE TABLE tb_piches (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    date_expiration DATE NOT NULL,
    date_payment DATE,
    price DECIMAL(10,2) NOT NULL,
    type VARCHAR(255) NOT NULL,
    id_category BIGINT UNSIGNED NOT NULL,
    id_client BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (id_category) REFERENCES tb_categorys(id) ON DELETE CASCADE,
    FOREIGN KEY (id_client) REFERENCES tb_clients(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
