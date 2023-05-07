create table comment
(
    id          bigint unsigned                        not null comment 'Id'
        primary key,
    video_id    bigint                                 not null comment '视频id',
    use_id      bigint                                 not null comment '消息发送人',
    content     varchar(255) default ''                not null comment '评论内容',
    create_date datetime     default CURRENT_TIMESTAMP not null,
    update_date datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

create table message
(
    id           bigint                             not null comment 'Id'
        primary key,
    from_user_id bigint                             not null comment '消息发送者',
    to_user_id   bigint                             not null comment '消息接收者',
    content      varchar(255)                       not null comment '消息内容',
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

create table relation
(
    id           bigint unsigned not null comment 'Id'
        primary key,
    from_user_id int             not null comment '关注者',
    to_user_Id   int             not null comment '被关注者'
);

create index idx_fromID_toID
    on relation (from_user_id, to_user_Id);

create table user
(
    id               bigint                                 not null comment 'Id'
        primary key,
    name             varchar(255) default ''                not null comment '用户名称',
    password         varchar(255)                           not null comment '加密后的密码',
    follow_count     bigint       default 0                 not null comment '关注总数',
    follower_count   bigint       default 0                 not null comment '粉丝总数',
    avatar           varchar(255) default ''                not null comment '用户头像',
    background_image varchar(255) default ''                not null comment '用户个人页顶部大图',
    signature        varchar(255) default ''                not null comment '个人简介',
    total_favorited  bigint       default 0                 not null comment '获赞数量',
    work_count       bigint       default 0                 not null comment '作品数',
    favorite_count   bigint       default 0                 not null comment '喜欢数',
    create_time      datetime     default CURRENT_TIMESTAMP not null,
    update_time      datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint idx_name
        unique (name)
);

create table user_like
(
    id       bigint unsigned auto_increment comment 'Id'
        primary key,
    user_id  bigint unsigned not null comment '用户id',
    video_id bigint unsigned not null comment '视频id'
);

create index idx_userID_videoID
    on user_like (user_id, video_id);

create table video
(
    id             bigint unsigned auto_increment comment 'Id'
        primary key,
    play_url       varchar(255) default ''                not null comment '视频播放地址',
    cover_url      varchar(255) default ''                not null comment '视频封面地址',
    favorite_count bigint       default 0                 not null comment '视频的点赞总数',
    comment_count  bigint       default 0                 not null comment '视频的评论总数',
    title          varchar(255) default ''                not null comment '视频标题',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
);

