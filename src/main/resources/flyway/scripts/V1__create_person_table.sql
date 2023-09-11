CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    middle_name VARCHAR(45) NOT NULL,
    age TINYINT NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'NON-BINARY', 'OTHER') NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(13) NOT NULL
);
