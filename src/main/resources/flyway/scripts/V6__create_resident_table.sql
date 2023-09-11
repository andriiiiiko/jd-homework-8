CREATE TABLE resident (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_entry_permission BOOLEAN NOT NULL,
    apartment_id INT NOT NULL,
    person_id INT NOT NULL,
    FOREIGN KEY (apartment_id) REFERENCES apartment(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);
