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
    using_mfa BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login DATETIME DEFAULT NULL,
    image_url VARCHAR(255) DEFAULT 'https://cdn-icons-png.flaticon.com/512/149/149074.png',
    CONSTRAINT UQ_Users_Email UNIQUE (email)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

#INSERT INTO Users (first_name, last_name, email, password, address, phone, title, bio, enabled, non_locked, using_nfa, created_at, image_url)
#VALUES ('John', 'Doe', 'johndoe@example.com', 'mypassword', '123 Main St', '555-1234', 'Manager', 'Lorem ipsum dolor sit amet', TRUE, TRUE, FALSE, NOW(), 'https://cdn-icons-png.flaticon.com/512/149/149074.png');


DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    permission VARCHAR(50) NOT NULL,
    CONSTRAINT UQ_Roles_Name UNIQUE (name)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS UserRoles;
CREATE TABLE UserRoles (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT UQ_UserRoles_User_Id UNIQUE (user_id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS Events;
CREATE TABLE Events (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL CHECK (type IN ('LOGIN_ATTEMPT', 'LOGIN_ATTEMPT_FAILURE', 'LOGIN_ATTEMPT_SUCCESS', 'PROFILE_UPDATE', 'PROFILE_PICTURE_UPDATE', 'ROLE_UPDATE', 'ACCOUNT_SETTINGS_UPDATE', 'PASSWORD_UPDATE', 'MFA_UPDATE')),
    description VARCHAR(255) NOT NULL,
    CONSTRAINT UQ_Events_Type UNIQUE (type)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS UserEvents;
CREATE TABLE UserEvents (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    event_id BIGINT UNSIGNED NOT NULL,
    device VARCHAR(100) DEFAULT NULL,
    ip_address VARCHAR(100) DEFAULT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Events(id) ON DELETE RESTRICT ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS AccountVerifications;
CREATE TABLE AccountVerifications (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    url VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_AccountVerifications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_AccountVerifications_Url UNIQUE (url)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS ResetPasswordVerifications;
CREATE TABLE ResetPasswordVerifications (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    url VARCHAR(255) NOT NULL,
    expiration_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_ResetPasswordVerifications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_ResetPasswordVerifications_Url UNIQUE (url)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS TwoFactorAuthentications;
CREATE TABLE TwoFactorAuthentications (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    code VARCHAR(10) NOT NULL,
    expiration_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_TwoFactorAuthentications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_TwoFactorAuthentications_Code UNIQUE (code)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
