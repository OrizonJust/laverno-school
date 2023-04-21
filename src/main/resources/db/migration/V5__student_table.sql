create table if not exists school.sc_t_student
(
    usr_id uuid not null,
    c_id uuid not null,
    s_start_date timestamp not null,
    primary key (usr_id, c_id)
);