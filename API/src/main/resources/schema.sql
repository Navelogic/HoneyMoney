# Author: Navelogic
# Date: 05/06/2023
# Version: 1.0

/*
 * Rules:
    * 1. Table names are plural
    * 2. Table names are snake_case
    * 3. Spell out id in table names (item_id, user_id, etc.)
    * 4. Use caps for SQL keywords (SELECT, FROM, WHERE, etc.)
 */

CREATE SCHEMA IF NOT EXISTS dbhoneymoney;
SET NAMES 'UTF8MB4';
SET TIME_ZONE = 'America/Sao_Paulo';

USE dbhoneymoney;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) DEFAULT NULL,
    address VARCHAR(255) DEFAULT NULL,
    phone VARCHAR(30) DEFAULT NULL,
    title VARCHAR(50) DEFAULT NULL,
    bio VARCHAR(255) DEFAULT NULL,
    enabled BOOLEAN DEFAULT FALSE,
    non_locked BOOLEAN DEFAULT TRUE,
    using_nfa BOOLEAN DEFAULT FALSE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    image_url VARCHAR(255) DEFAULT 'https://cdn-icons-png.flaticon.com/512/149/149074.png',
    CONSTRAINT UQ_Users_Email UNIQUE (email)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;