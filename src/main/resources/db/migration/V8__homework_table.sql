create table if not exists school.sc_t_homework
(
    s_id uuid not null,
    p_id uuid not null,
    usr_id uuid,
    h_done boolean default false,
    primary key (s_id, p_id)
);