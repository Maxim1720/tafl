INSERT INTO permission (id, name) values (1, 'READ'), (2, 'WRITE'), (3, 'WRITE_USERS'), (4, 'WRITE_ROLES');
INSERT INTO role (id, name) values (1, 'USER'), (2, 'ADMIN'), (3, 'MODERATOR');
INSERT INTO role_permissions values (1,1), (2,3);