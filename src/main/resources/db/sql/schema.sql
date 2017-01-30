/**
 * Author:  Emil Forslund
 * Created: Jan 30, 2017
 */
create database `securerest`;
use `securerest`;

create table `account` (
    `id` bigint not null auto_increment primary key,
    `username` varchar(30) not null unique,
    `password` char(60) not null,
    `role` enum('USER', 'ADMIN') not null
);