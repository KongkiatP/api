USE
`interview` ;

CREATE TABLE `event`
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    cost     DECIMAL(10, 2) NOT NULL,
    duration FLOAT          NOT NULL,
    location VARCHAR(255)   NOT NULL
);

INSERT INTO `event` (cost, duration, location)
VALUES (100.00, 1.2345, 'Bangkok'),
       (200.50, 2.4567, 'Chiang Mai'),
       (150.75, 1.6789, 'Bangkok'),
       (300.00, 3.1234, 'Phuket'),
       (120.00, 0.9876, 'Bangkok'),
       (220.45, 1.5432, 'Chiang Mai'),
       (190.80, 2.8765, 'Phuket'),
       (250.00, 3.3333, 'Chiang Mai'),
       (210.60, 1.1111, 'Bangkok'),
       (175.00, 2.2222, 'Phuket'),
       (205.30, 1.4321, 'Bangkok'),
       (198.45, 2.6543, 'Phuket'),
       (150.00, 0.5678, 'Chiang Mai'),
       (250.00, 3.8765, 'Bangkok'),
       (130.75, 1.3456, 'Phuket');