INSERT INTO app.country(code, name)
VALUES ('CN', '中国'),
       ('US', '美国');
INSERT INTO app.user (id, created_at, created_by, object_version_number, updated_at, updated_by, address, birthday,
                      description, display_name, email, enabled, gender, password, phone_number, username,
                      country_code)
VALUES (1, '2020-08-27 13:38:22.000000', 1, 1, '2020-08-27 13:38:32.000000', 1, NULL, '2020-08-27', NULL, 'Super User',
        'admin@mail.com', TRUE, NULL, '{bcrypt}$2a$10$R7Vy1mt8Ar6ECkR8h/mJje0QXG4EoS0Q7Jl5yX04pStUmKSz.ZfBO', NULL,
        'super', 'CN'),
       (2, '2020-08-27 13:38:22.000000', 1, 1, '2020-08-27 13:38:32.000000', 1, NULL, '2020-08-27', NULL, 'Normal User',
        'admin@mail.com', TRUE, NULL, '{bcrypt}$2a$10$R7Vy1mt8Ar6ECkR8h/mJje0QXG4EoS0Q7Jl5yX04pStUmKSz.ZfBO', NULL,
        'user', 'CN');
INSERT INTO app.role(id, created_at, created_by, object_version_number, updated_at, updated_by, description, role_code,
                     role_name)
VALUES (1, '2020-08-27 13:38:22.000000', 1, 1, '2020-08-27 13:38:32.000000', 1, NULL, 'ROLE_ADMIN', '管理员'),
       (2, '2020-08-27 13:38:22.000000', 1, 1, '2020-08-27 13:38:32.000000', 1, NULL, 'ROLE_USER', '用户');
INSERT INTO app.user_roles
VALUES (1, 1),
       (2, 2);

# Internationalization
INSERT INTO app.language(code, `primary`, secondary)
VALUES ('zh-CN', 'Chinese', 'Simplified'),
       ('zh-TW', 'Chinese', 'Traditional'),
       ('en-US', 'English', 'US'),
       ('en-UK', 'English', 'UK');

TRUNCATE TABLE user_roles;
TRUNCATE TABLE employee_roles;
DELETE FROM role;
DELETE FROM user;