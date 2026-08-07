CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    price REAL NOT NULL,
    CONSTRAINT name_unique UNIQUE (name)
);