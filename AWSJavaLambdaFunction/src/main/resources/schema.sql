CREATE TABLE pet (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     breed VARCHAR(255),
                     color VARCHAR(255),
                     name VARCHAR(255),
                     type VARCHAR(255) CHECK (type IN ('DOG', 'CAT', 'FISH', 'BIRD'))
);