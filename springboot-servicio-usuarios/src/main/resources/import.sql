insert into usuarios (user_name, user_password, enabled, nombre, apellidos, email, create_date, birth_date) values ('robinare', '$2a$10$yksquDoARWlYwDClkQaKO.mDKmtmMbUygyvvARs9Uy7.7pkUo0Ee2', true, 'Robin', 'Arellano', 'robinare@gmail.com', now(), '1987-04-27');
insert into usuarios (user_name, user_password, enabled, nombre, apellidos, email, create_date, birth_date) values ('argenitare', '$2a$10$0syuvatGMK6C1EkNoUNCoeD2r/1sjmXSYYM3se9R4yFq4U1L7uKDy', true, 'Argenit', 'Arellano', 'argenitare@gmail.com', now(), '1983-02-19');

insert into roles (role_name) values ('ROLE_ADMIN');
insert into roles (role_name) values ('ROLE_USER');

insert into usuarios_roles (usuario_id, role_id) values (1, 1);
insert into usuarios_roles (usuario_id, role_id) values (1, 2);
insert into usuarios_roles (usuario_id, role_id) values (2, 2);