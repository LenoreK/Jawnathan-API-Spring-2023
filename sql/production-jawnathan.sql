drop database if exists jawnathan;
create database jawnathan;
use jawnathan;

create table venue (
	venue_id int primary key auto_increment,
    venue_name varchar(300) not null,
    url varchar(300),
    address varchar(200) not null,
    city varchar(30) not null,
    state varchar(2) not null,
    zipcode varchar(10) not null
);

create table video (
	video_id int primary key auto_increment,
    video_url varchar(500) not null,
    video_name varchar(100) not null
);

create table person (
	person_id int primary key auto_increment,
    first_name varchar(50) not null,
    middle_name varchar(50),
    last_name varchar(50) not null
);

create table musical_group (
	group_id int primary key auto_increment,
    group_name varchar(100) not null,
    genre varchar(40) not null,
    group_photo_url varchar(500),
    group_website_url varchar(320)
);

create table album (
	album_id int primary key auto_increment,
    album_name varchar(100) not null,
    release_year int not null,
    photo_url varchar(320),
    buy_album_url varchar(320)
);

create table song (
	song_id int primary key auto_increment,
    song_name varchar(50) not null,
    listen_to_song varchar(320) not null,
    photo_url varchar(320),
    buy_song_url varchar(320),
    album_id int,
    group_id int not null,
    constraint fk_album_song_id
		foreign key (album_id)
        references album(album_id),
	constraint fk_group_song_id
		foreign key (group_id)
        references musical_group(group_id)
);

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(320) not null,
    password_hash varchar(2048) not null,
    enabled bit not null default(1)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

create table gig (
	gig_id int primary key auto_increment,
    gig_date date not null,
    gig_end_date date,
    details varchar(1000),
    start_time time,
    end_time time,
    venue_id int not null,
    constraint fk_gig_venue_id
		foreign key (venue_id)
        references venue(venue_id)
);

create table person_role (
	person_role_id int primary key auto_increment,
    person_role_name varchar(50) not null
);

create table person_gig_role (
	person_id int not null,
    gig_id int not null,
    person_role_id int not null,
    constraint pk_person_gig_role
		primary key(person_id, gig_id, person_role_id),
	constraint fk_person_gig_role_person_id
		foreign key(person_id)
        references person(person_id),
	constraint fk_person_gig_role_gig_id
		foreign key(gig_id)
        references gig(gig_id),
	constraint fk_person_gig_role_role_id
		foreign key(person_role_id)
        references person_role(person_role_id)
);

create table group_gig (
	group_id int not null,
    gig_id int not null,
    constraint pk_group_gig_id
		primary key(group_id, gig_id),
	constraint fk_group_gig_group_id
		foreign key(group_id)
        references musical_group(group_id),
	constraint fk_group_gig_gig_id
		foreign key(gig_id)
        references gig(gig_id)
);

create table group_video (
	group_id int not null,
    video_id int not null,
    constraint pk_group_video_id
		primary key(group_id, video_id),
	constraint fk_group_video_group_id
		foreign key(group_id)
        references musical_group(group_id),
	constraint fk_group_video_video_id
		foreign key(video_id)
        references video(video_id)
);

create table group_person (
	group_id int not null,
    person_id int not null,
    person_role_id int not null,
    constraint pk_group_person_id
		primary key(group_id, person_id, person_role_id),
	constraint fk_group_person_group_id
		foreign key(group_id)
        references musical_group(group_id),
	constraint fk_group_person_person_id
		foreign key(person_id)
        references person(person_id),
	constraint fk_person_group_role_id
		foreign key(person_role_id)
        references person_role(person_role_id)
);