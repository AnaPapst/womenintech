drop database if exists WIT;
create database WIT;
use WIT;

create table Users (

email		varchar(100) UNIQUE primary key,
nome	    varchar (100),
senha		varchar (30),
cpf			int

)engine=InnoDB;