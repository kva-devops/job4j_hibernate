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

-- Candidate
create table candidates (
    id serial primary key,
    name varchar (255),
    experience varchar (255),
    salary int,
    basevacancies_id int references base(id)
);

-- Vacancy and Base Vacancies
create table vacancies (
    id serial primary key,
    name varchar (255),
    description varchar (255),
    salary int,
    phone varchar (255)
);

create table base (
    id serial primary key,
    name varchar (255)
);

create table base_vacancies (
    basevacancies_id int references base(id),
    vacancylist_id int references vacancies(id)
);