CREATE TABLE ticket_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    basePrice DECIMAL(10, 2)
);
INSERT INTO ticket_type (name, basePrice)
VALUES ('Adult', 25.00),
       ('Senior', NULL),
       ('Teen', 12.00),
       ('Children', 5.00);
CREATE TABLE age_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    minAge INT NOT NULL,
    maxAge INT
);
INSERT INTO age_category (name, MinAge, MaxAge)
VALUES
    ('Adult', 18, 64),
    ('Senior', 65, NULL),
    ('Teen', 11, 17),
    ('Children', 0, 10);


