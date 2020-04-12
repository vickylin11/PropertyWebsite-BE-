CREATE TABLE property (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    type VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    purpose VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    price bigint(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    image VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;