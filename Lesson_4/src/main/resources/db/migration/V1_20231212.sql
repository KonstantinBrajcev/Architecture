CREATE TABLE customer
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(64) NOT NULL UNIQUE,
    password   VARCHAR(64) NOT NULL,
    role       SMALLINT NOT NULL,
    balance    FLOAT NOT NULL
);
CREATE TABLE bus
(
    id          SERIAL PRIMARY KEY,
    route       VARCHAR(5) NOT NULL,
    departure   TIMESTAMP NOT NULL,
    arrival     TIMESTAMP NOT NULL
);

CREATE TABLE ticket
(
    id         SERIAL PRIMARY KEY,
    price      FLOAT NOT NULL,
    bus_id     INTEGER NOT NULL REFERENCES bus (id),
    sold       BOOLEAN DEFAULT FALSE
);

CREATE TABLE customer_ticket
(
    id             SERIAL PRIMARY KEY,
    customer_id    INTEGER NOT NULL REFERENCES customer (id),
    ticket_id      INTEGER NOT NULL REFERENCES ticket (id)
);

INSERT INTO customer
(name, password, role, balance)
VALUES ('Peter',
        'test',
        0,
        1500),
       ('John',
        'test2',
        1,
        1000),
       ('Jane',
        'test3',
        1,
        800)
;

INSERT INTO bus
(route, departure, arrival)
VALUES ('15F', '2023-12-22 10:00:00', '2023-12-22 12:00:00'),
       ('8A', '2023-12-15 08:00:00', '2023-12-15 13:15:00'),
       ('123B', '2023-12-14 06:00:00', '2023-12-14 09:27:00'),
       ('C56D', '2023-12-18 13:45:00', '2023-12-18 22:09:00')
;

INSERT INTO ticket
(price, bus_id)
VALUES (30, 1),
       (30, 1),
       (30, 1),
       (30, 1),
       (30, 2),
       (30, 2),
       (30, 2),
       (30, 2),
       (30, 2),
       (30, 2),
       (30, 2),
       (30, 3),
       (30, 3),
       (39, 4),
       (39, 4),
       (39, 4),
       (39, 4),
       (39, 4),
       (39, 4),
       (39, 4),
       (39, 4)
;

UPDATE ticket
SET sold = TRUE
WHERE id = 1;

INSERT INTO customer_ticket
(customer_id, ticket_id)
VALUES (1, 1);