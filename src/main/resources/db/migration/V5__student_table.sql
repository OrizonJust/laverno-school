create table if not exists school.sc_t_student
(
    usr_id       uuid      not null,
    c_id         uuid      not null,
    st_start_date timestamp not null,
    st_done       boolean default false,
    st_disable    boolean default false,
    primary key (usr_id, c_id)
);