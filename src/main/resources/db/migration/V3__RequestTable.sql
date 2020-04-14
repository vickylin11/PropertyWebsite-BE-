CREATE TABLE request (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    user_id bigint(20) DEFAULT NULL,
    property_id bigint(20) DEFAULT NULL,
    is_resolved bit NOT NULL DEFAULT 0,
    created_at datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;