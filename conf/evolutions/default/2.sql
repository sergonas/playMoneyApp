# Users schema

# --- !Ups

INSERT INTO accounts (name, balance) VALUES ('Наличные', 3000);

# --- !Downs

DELETE FROM accounts WHERE name = 'Наличные';