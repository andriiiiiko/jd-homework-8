CREATE TABLE participant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ownership_right BOOLEAN NOT NULL,
    osbb_role_id INT NOT NULL,
    person_id INT NOT NULL,
    FOREIGN KEY (osbb_role_id) REFERENCES osbb_role(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);
