DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS item_type;
DROP TABLE IF EXISTS item CASCADE;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE user_role
(
    id      INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX user_role_unique_name_idx ON item (name);

CREATE TABLE user
(
    id                  INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name                VARCHAR(255)            NOT NULL,
    email               VARCHAR(255)            NOT NULL,
    password            VARCHAR(255)            NOT NULL,
    date_registered     TIMESTAMP DEFAULT now() NOT NULL,
    enabled             BOOLEAN   DEFAULT TRUE  NOT NULL,
    user_role_id        INTEGER                 NOT NULL,
    FOREIGN KEY (user_role_id) REFERENCES user_role (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX user_unique_email_idx ON user (email);

CREATE TABLE item_type
(
    id   INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX item_type_unique_name_idx ON item_type (name);

CREATE TABLE item
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name            VARCHAR(255)    NOT NULL,
    date_memorable  DATE            NOT NULL,
    item_type_id    INTEGER         NOT NULL,
    CONSTRAINT name_item_type_id_idx UNIQUE (name, item_type_id),
    FOREIGN KEY (item_type_id) REFERENCES item_type (id) ON DELETE CASCADE
);
