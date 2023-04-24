create table if not exists school.sc_t_discipline
(
    d_id      uuid primary key default public.uuid_generate_v4(),
    d_name    varchar(150) not null,
    d_disable boolean          default false
);