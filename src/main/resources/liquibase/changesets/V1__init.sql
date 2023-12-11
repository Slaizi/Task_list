CREATE TABLE if not exists users
(
  id bigserial primary key,
  name VARCHAR(255) not null,
  username VARCHAR(255) not null unique,
  password VARCHAR(255) not null
);

CREATE TABLE if not exists tasks
(
  id bigserial primary key,
  title VARCHAR(255) not null,
  description VARCHAR(255) null,
  status VARCHAR(255) not null,
  expiration_date timestamp null
);

CREATE TABLE if not exists users_tasks
(
  user_id bigint not null,
  task_id bigint not null,
  primary KEY(user_id, task_id),
  constraint fk_users_tasks_users foreign KEY(user_id) references USERS(id) on delete cascade on update no action,
  constraint fk_users_tasks_tasks foreign KEY(task_id) references TASKS(id) on delete cascade on update no action
);

CREATE TABLE if not exists user_roles
(
  user_id bigint not null,
  role VARCHAR(255) not null,
  primary KEY(user_id, role),
  constraint fk_users_roles_users foreign KEY(user_id) references USERS(id) on delete cascade on update no action
)