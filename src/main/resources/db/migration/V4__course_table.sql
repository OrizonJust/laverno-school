create table if not exists school.sc_t_course
(
    c_id uuid primary key default public.uuid_generate_v4(),
    c_name varchar(100) not null,
    c_start_ts timestamp not null,
    c_end_ts timestamp not null,
    c_disable boolean not null,
    d_id uuid not null,
    constraint d_id_fk foreign key (d_id) references school.sc_t_discipline (d_id)
);