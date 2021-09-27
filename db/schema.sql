CREATE TABLE car_brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE car_model (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE models (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2000),
    brand_id INT NOT NULL REFERENCES brands(id)
);