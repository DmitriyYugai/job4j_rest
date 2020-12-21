drop table if exists employees_accounts;
drop table if exists person;
drop table if exists employees;


create table person (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000)
);

insert into person (login, password) values ('parsentev', '123');
insert into person (login, password) values ('ban', '123');
insert into person (login, password) values ('ivan', '123');

select * from person;

select * from employees;