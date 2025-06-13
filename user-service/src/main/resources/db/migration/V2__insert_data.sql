INSERT INTO tb_user (email, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ('julio@gmail.com', 'Julio Cesar Coutinho', '1e3cdeeaaaeeda173ff6d002e7cb5e3f91ebc354dcff52156c9eaba1793a3a5e5bee306c11099e22', TRUE, TRUE, TRUE, TRUE);
INSERT INTO tb_user (email, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES  ('priscila@gmail.com', 'Priscila Aquino Coutinho', '362ad02420268beeb22d3a1f0d92749df461d7f4b74c9433d7415bdeef1b2902f4eb1edaecb37cb3', TRUE, TRUE, TRUE, TRUE);

INSERT INTO tb_permission (description) VALUES ('ADMIN');
INSERT INTO tb_permission (description) VALUES ('MANAGER');
INSERT INTO tb_permission (description) VALUES ('COMMON_USER');

INSERT INTO tb_user_permission (id_user, id_permission) VALUES (1, 1);
INSERT INTO tb_user_permission (id_user, id_permission) VALUES (2, 1);
INSERT INTO tb_user_permission (id_user, id_permission) VALUES (1, 2);
