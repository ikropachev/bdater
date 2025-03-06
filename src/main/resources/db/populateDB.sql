DELETE FROM user_role;
DELETE FROM user;
DELETE FROM item_type;
DELETE FROM item;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO user_role (name)
VALUES ('ADMIN'),   --100000
       ('USER');    --100001

INSERT INTO user (name, email, password, registered, user_role_id)
VALUES ('admin', 'admin@test.test', 'admin', '2021-12-02 18:00:00', 100000),    --100002
       ('user', 'user@gmail.com', 'user', '2021-12-02 18:01:00', 100001);       --100003

INSERT INTO item_type (name)
VALUES ('GAME'),    --100004
       ('PERSON');  --100005

INSERT INTO item (name, date_memorable, item_type_id)
VALUES ('Devil May Cry 5', '2019-03-08', 100004),   --100006
       ('Курт Рассел', '1951-03-17', 100005);       --100007