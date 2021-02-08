CREATE DATABASE hibernatedb;

CREATE TABLE person(
	person_id serial not null primary key,
	title varchar(5),
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	middle_name varchar(50) not null,
	suffix varchar(5),
	street_number integer,
	address_barangay varchar(250),
	addr_municipality varchar(250),
	zip_code integer,
	birthday date not null,
	gradeWeightedAverage decimal,
	date_hired date not null,
	employment_status text		
);

CREATE TABLE contactinfo(
	contact_id serial not null primary key,
	landline numeric,
	mobile numeric,
	email varchar(250),
	person_id bigint references person(person_id)
);
	
CREATE TABLE roles(
	role_id serial primary key,
	role varchar(50)
);

CREATE TABLE person_role (
	person_id  bigint references person(person_id),
	role_id bigint references roles(role_id )
);

create sequence HIBERNATE_SEQUENCE 
minvalue 1 
maxvalue 2147483647 
start with 1000 
increment by 1 
cache 1;