DELETE FROM user_role;
DELETE FROM user;
DELETE FROM item_type;
DELETE FROM item;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO user (name, email, password, registered)
VALUES ('admin', 'admin@test.test', 'admin', '2021-12-02 18:00:00'),    --100000
       ('user', 'user@test.test', 'user', '2021-12-02 18:01:00');       --100001

INSERT INTO item_type (name)
VALUES ('GAME'),    --100002
       ('PERSON');  --100003

INSERT INTO item (name, date_memorable, item_type_id)
VALUES ('Devil May Cry 5', '2019-03-08', 100002),   --100004
       ('Курт Рассел', '1951-03-17', 100003);       --100005

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100000),   --100006
       ('USER', 100001);    --100007