CREATE TABLE ticket_type (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    BASE_PRICE DECIMAL(10, 2)
);
INSERT INTO ticket_type (NAME, BASE_PRICE)
VALUES ('Adult', 25.00),
       ('Senior', NULL),
       ('Teen', 12.00),
       ('Children', 5.00);
CREATE TABLE age_category (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    MIN_AGE INT NOT NULL,
    MAX_AGE INT
);
INSERT INTO age_category (NAME, MIN_AGE, MAX_AGE)
VALUES
    ('Adult', 18, 64),
    ('Senior', 65, NULL),
    ('Teen', 11, 17),
    ('Children', 0, 10);


