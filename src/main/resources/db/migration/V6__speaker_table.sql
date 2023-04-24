create table if not exists school.sc_t_speaker
(
    usr_id uuid not null,
    c_id uuid not null,
    sp_start_date timestamp not null,
    sp_disable boolean default false,
    primary key (usr_id, c_id)
);