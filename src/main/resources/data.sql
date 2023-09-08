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