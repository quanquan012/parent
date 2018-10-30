create database broker;

drop table if exists wx_user;

/*==============================================================*/
/* Table: wx_user                                               */
/*==============================================================*/
create table wx_user
(
   user_id              bigint not null AUTO_INCREMENT,
   user_name            char(255),
   user_phone           char(15),
   user_access_time     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   open_id              char(50) not null,
   primary key (user_id)
);

alter table wx_user comment '微信用户';
