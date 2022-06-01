drop database if exists androidapi;
create database androidapi;
use androidapi;

create table Personnages(
    id int not null auto_increment,
    name varchar(50),
    image varchar(50),
    description varchar(50),
    primary key (id)
);

insert into Personnage values (null,"erwann","rien","ceci est un test");