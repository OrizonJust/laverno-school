create table school.sc_t_role
(
    r_id   uuid primary key default public.uuid_generate_v4(),
    r_name varchar(20) not null
);

create table school.sc_t_user_x_role
(
    usr_id uuid not null,
    r_id uuid not null,
    primary key (usr_id, r_id),
    constraint usr_sub_id_fk foreign key (usr_id) references school.sc_t_user (usr_id),
    constraint r_sub_id_fk foreign key (r_id) references school.sc_t_role (r_id)
);