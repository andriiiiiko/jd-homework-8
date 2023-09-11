CREATE TABLE apartment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    floor TINYINT NOT NULL,
    number INT NOT NULL,
    area DECIMAL(5, 2) NOT NULL,
    building_id INT NOT NULL,
    FOREIGN KEY (building_id) REFERENCES building(id)
);
