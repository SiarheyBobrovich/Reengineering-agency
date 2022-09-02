\connect monitor

CREATE SCHEMA IF NOT EXISTS security
    AUTHORIZATION postgres;

CREATE TABLE security.users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled boolean NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE security.authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES security.users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on security.authorities (username,authority);