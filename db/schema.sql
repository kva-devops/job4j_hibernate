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

-- Cars and owners
create table engine (
        id serial primary key,
        model varchar (255),
        power int
);

create table driver (
        id serial primary key,
        name varchar(255)
);

create table car (
        id serial primary key,
        name varchar(255),
        engine_id int not null unique references engine(id)
);

create table history_owner (
        id serial primary key,
        driver_id int not null references driver(id),
        car_id int not null references car(id)
);