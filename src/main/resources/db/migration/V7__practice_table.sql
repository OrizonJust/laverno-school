create table if not exists school.sc_t_practice
(
    p_id          uuid primary key default public.uuid_generate_v4(),
    p_name        varchar(150) not null,
    p_description varchar      not null,
    p_order_num   integer      not null,
    p_disable     boolean          default false,
    d_id          uuid         not null,
    constraint d_id_fk foreign key (d_id) references school.sc_t_discipline (d_id)
);