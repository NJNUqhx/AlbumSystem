create table admin
(
    admin_id int auto_increment
        primary key,
    account  varchar(16)  null,
    password varchar(128) null,
    constraint admin_id
        unique (admin_id)
);

create table comment_to_album
(
    comment_id int not null,
    album_id   int not null,
    primary key (comment_id, album_id)
);

create table user
(
    user_id  int auto_increment
        primary key,
    account  varchar(16)  null,
    password varchar(128) null,
    nickname varchar(16)  null,
    constraint user_id
        unique (user_id)
);

create table album
(
    album_id    int auto_increment
        primary key,
    user_id     int                not null,
    authority   smallint default 0 null,
    name        varchar(16)        null,
    star        int      default 0 null,
    time        datetime           null,
    description varchar(128)       null,
    constraint album_id
        unique (album_id),
    constraint album_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
);

create table comment
(
    comment_id int auto_increment
        primary key,
    user_id    int           not null,
    content    varchar(128)  not null,
    time       datetime      null,
    status     int default 0 null,
    constraint comment_id
        unique (comment_id),
    constraint comment_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
);

create table comment_handle_result
(
    id         int auto_increment,
    comment_id int          not null,
    user_id    int          not null,
    result     varchar(16)  null,
    advice     varchar(128) null,
    admin_id   int          not null,
    time       datetime     null,
    constraint id
        unique (id),
    constraint comment_handle_result_admin_admin_id_fk
        foreign key (admin_id) references admin (admin_id),
    constraint comment_handle_result_comment_comment_id_fk
        foreign key (comment_id) references comment (comment_id),
    constraint comment_handle_result_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table comment_report
(
    report_id  int auto_increment
        primary key,
    comment_id int                not null,
    user_id    int                not null,
    reason     varchar(128)       null,
    status     smallint default 0 null,
    time       datetime           null,
    constraint report_id
        unique (report_id),
    constraint comment_report_comment_comment_id_fk
        foreign key (comment_id) references comment (comment_id),
    constraint comment_report_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
);

create table friend
(
    user1_id int not null,
    user2_id int not null,
    primary key (user1_id, user2_id),
    constraint friend_user_user_id_fk
        foreign key (user1_id) references user (user_id),
    constraint friend_user_user_id_fk_2
        foreign key (user2_id) references user (user_id)
            on update cascade on delete cascade
);

create table friend_application
(
    id           int auto_increment
        primary key,
    applicant_id int                not null,
    recipient_id int                not null,
    status       smallint default 0 null,
    message      varchar(128)       null,
    constraint id
        unique (id),
    constraint friend_application_user_user_id_fk
        foreign key (applicant_id) references user (user_id)
            on update cascade on delete cascade,
    constraint friend_application_user_user_id_fk_2
        foreign key (recipient_id) references user (user_id)
);

create table moment
(
    moment_id   int auto_increment
        primary key,
    user_id     int                not null,
    authority   smallint default 0 null,
    description varchar(128)       null,
    star        int      default 0 null,
    time        datetime           null,
    status      int      default 0 null,
    constraint moment_id
        unique (moment_id),
    constraint moment_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table comment_to_moment
(
    comment_id int not null,
    moment_id  int not null,
    primary key (comment_id, moment_id),
    constraint comment_to_moment_comment_comment_id_fk
        foreign key (comment_id) references comment (comment_id)
            on update cascade on delete cascade,
    constraint comment_to_moment_moment_moment_id_fk
        foreign key (moment_id) references moment (moment_id)
            on update cascade on delete cascade
);

create table moment_examine_result
(
    id        int auto_increment
        primary key,
    moment_id int          not null,
    user_id   int          not null,
    result    varchar(128) null,
    advice    varchar(128) null,
    admin_id  int          not null,
    time      datetime     null,
    constraint id
        unique (id),
    constraint moment_examine_result_admin_admin_id_fk
        foreign key (admin_id) references admin (admin_id)
            on update cascade on delete cascade,
    constraint moment_examine_result_moment_moment_id_fk
        foreign key (moment_id) references moment (moment_id)
            on update cascade on delete cascade,
    constraint moment_examine_result_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
);

create table moment_handle_result
(
    id        int auto_increment
        primary key,
    moment_id int          not null,
    user_id   int          not null,
    result    varchar(128) null,
    advice    varchar(128) null,
    admin_id  int          not null,
    time      datetime     null,
    constraint id
        unique (id),
    constraint moment_handle_result_admin_admin_id_fk
        foreign key (admin_id) references admin (admin_id),
    constraint moment_handle_result_moment_moment_id_fk
        foreign key (moment_id) references moment (moment_id),
    constraint moment_handle_result_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table moment_report
(
    report_id int auto_increment
        primary key,
    moment_id int                not null,
    user_id   int                not null,
    reason    varchar(128)       null,
    status    smallint default 0 null,
    time      datetime           null,
    constraint report_id
        unique (report_id),
    constraint moment_report_moment_moment_id_fk
        foreign key (moment_id) references moment (moment_id)
            on update cascade on delete cascade,
    constraint moment_report_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table photo
(
    photo_id  int auto_increment
        primary key,
    user_id   int                not null,
    name      varchar(16)        null,
    authority smallint default 0 null,
    time      datetime           null,
    status    smallint           null,
    address   varchar(128)       null,
    constraint photo_id
        unique (photo_id),
    constraint photo_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
);

create table photo_examine_result
(
    id       int auto_increment
        primary key,
    photo_id int          not null,
    user_id  int          not null,
    result   varchar(16)  null,
    advice   varchar(128) null,
    admin_id int          not null,
    time     datetime     null,
    constraint id
        unique (id),
    constraint photo_examine_result_admin_admin_id_fk
        foreign key (admin_id) references admin (admin_id),
    constraint photo_examine_result_photo_photo_id_fk
        foreign key (photo_id) references photo (photo_id),
    constraint photo_examine_result_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table photo_to_album
(
    photo_id int not null,
    album_id int not null,
    primary key (photo_id, album_id),
    constraint photo_to_album_album_album_id_fk
        foreign key (album_id) references album (album_id)
            on update cascade on delete cascade,
    constraint photo_to_album_photo_photo_id_fk
        foreign key (photo_id) references photo (photo_id)
            on update cascade on delete cascade
);

create table photo_to_moment
(
    photo_id  int not null,
    moment_id int not null,
    primary key (photo_id, moment_id),
    constraint photo_to_moment_moment_moment_id_fk
        foreign key (moment_id) references moment (moment_id),
    constraint photo_to_moment_photo_photo_id_fk
        foreign key (photo_id) references photo (photo_id)
            on update cascade on delete cascade
);

create table user_management_result
(
    id       int auto_increment
        primary key,
    admin_id int         not null,
    message  varchar(64) null,
    time     datetime    null,
    constraint id
        unique (id),
    constraint user_management_result_admin_admin_id_fk
        foreign key (admin_id) references admin (admin_id)
);

