CREATE TABLE user (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    password VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    first_name VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    last_name VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    type VARCHAR(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'client',
    is_login bit(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;