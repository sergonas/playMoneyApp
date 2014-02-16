# Users schema

# --- !Ups

CREATE table accounts (
  id bigserial NOT NULL,
  name varchar(255) NOT NULL,
  balance double precision NOT NULL,
  description text,
  constraint pk_account PRIMARY KEY (id)
);

create table incomes (
  id bigserial not null,
  balance_id bigint,
  name varchar(255) not null,
  amount double precision 	 not null,
  date date not null,
  description text,
  constraint pk_income primary key(id),
  constraint fk_income_account FOREIGN KEY (balance_id) REFERENCES accounts(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
);

create table outcomes (
  id bigserial not null,
  balance_id bigint,
  name varchar(255) not null,
  amount double precision 	 not null,
  date date not null,
  description text,
  constraint pk_outcome primary key(id),
  constraint fk_outcome_account FOREIGN KEY (balance_id) REFERENCES accounts(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
);

# --- !Downs

drop table incomes;
drop table outcomes;
drop table accounts;
