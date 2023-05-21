INSERT INTO permission (id, name) values (1, 'READ'), (2, 'WRITE'), (3, 'WRITE_USERS'), (4, 'WRITE_ROLES'), (5, 'TRANSACTION_MANAGE');
INSERT INTO role (id, name) values (1, 'USER'), (2, 'ADMIN'), (3, 'MODERATOR'), (4, 'PERMANENT'), (5, 'TEMPORARY'), (6, 'CASHIER');
INSERT INTO role_permissions values (1,1), (2,3), (4,1), (1,4);

INSERT INTO device_status (id, name) VALUES (1, 'free'), (2, 'busy');
INSERT INTO runnable_entity_type (id, name) VALUES (1, 'device'), (2, 'app');
