# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table eshop_user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  user_name                 varchar(255),
  password                  varchar(255),
  mobile_no                 varchar(255),
  email                     varchar(255),
  constraint pk_eshop_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table eshop_user;

SET FOREIGN_KEY_CHECKS=1;

