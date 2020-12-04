create table game
(
    id           uuid         not null
        constraint pk_game
            primary key,
    display_name varchar(100) not null
);

create table achievement
(
    id            uuid         not null
        constraint pk_achievement
            primary key,
    game_id       uuid         not null
        constraint fk_achievement_game
            references game
            on update restrict on delete cascade,
    display_name  varchar(100) not null,
    description   varchar(500) not null,
    icon          varchar(512),
    display_order bigint,
    created_on    timestamp    not null,
    updated_on    timestamp,
    constraint uq_achievement_game_id_display_order
        unique (game_id, display_order),
    constraint uq_achievement_game_id_display_name
        unique (game_id, display_name)
);