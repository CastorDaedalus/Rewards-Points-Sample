create table if not exists customer (
    id bigint AUTO_INCREMENT primary key,
    name varchar(255) not null,
    email varchar(255) not null
);

create table if not exists "TRANSACTION" (
    id bigint AUTO_INCREMENT primary key,
    amount double not null,
    transaction_date timestamp not null,
    transaction_type varchar(255) not null,
    customer_id bigint not null
);

create table if not exists rewards_points (
    id bigint AUTO_INCREMENT primary key,
    points int not null,
    transaction_id bigint not null,
    customer_id bigint not null
);

create table if not exists rewards_points_config (
    id bigint AUTO_INCREMENT primary key,
    points_per_dollar double not null,
    points_per_dollar_threshold double not null,
    points_per_dollar_multiplier double not null,
    points_per_dollar_multiplier_threshold double not null
);

alter table "TRANSACTION" add constraint IF NOT EXISTS fk_customer_id foreign key (customer_id) references customer(id);

alter table rewards_points add constraint IF NOT EXISTS fk_transaction_id foreign key (transaction_id) references "TRANSACTION"(id);

alter table rewards_points add constraint IF NOT EXISTS fk_customer_id foreign key (customer_id) references customer(id);