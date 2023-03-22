drop database if exists jawnathan_test;
create database jawnathan_test;
use jawnathan_test;

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

delimiter //
create procedure set_known_good_state()
begin

	delete from venue;
    alter table venue auto_increment = 1;

insert into venue(venue_id, venue_name, url, address, city, state, zipcode) values
	(1, "Up 'N Smoke", "https://weedstock.org/home", "474 Fleming Landing Road", "Townsend", "NJ", "19734"),
    (2, "Currans", "https://curransirishinnbensalem.com", "1909 Bristol Pike", "Bensalem", "PA", "19020"),
    (3, "Anchor Rock Club", "https://www.anchorrockclub.com/", "247 New York Ave.", "Atlantic City", "NJ", "08041");

insert into gig(gig_id, gig_date, gig_end_date, details, start_time, end_time, venue_id) values
	(1, '2024-06-25', Null, "Last minute gig at Currans in Bensalem. I’ll be playing two Irish songs I know.", '20:00', '23:00', 2),
    (2, '2024-04-28', '2024-04-30', "First Stop on our mums the word national tour", Null, NUll, 1),
    (3, '2024-04-20', Null, "Mums the Word will be playing at AC this 4-20.", '21:00', Null, 3);

insert into musical_group(group_id, group_name, genre, group_photo_url, group_website_url) values
	(1, "Mums the Word", "alternative rock", "https://images.pexels.com/photos/1763075/pexels-photo-1763075.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", Null),
    (2, "Jawnathan Sebastian Sellers", "folk rock", "https://scontent-atl3-1.xx.fbcdn.net/v/t39.30808-6/307377666_568501515277188_113188498116520371_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=_Iw7Jz6KvLYAX_uJKbI&_nc_ht=scontent-atl3-1.xx&oh=00_AfA2qrFpJS74zWUvSw51HWlTq5ePp-mYZm_lQNLDKKAJ1A&oe=641BAC5F", Null),
    (3, "Cheezy and the Crackers", "Reggae", "https://scontent-atl3-2.xx.fbcdn.net/v/t39.30808-6/223291674_239674261316227_4482802649013981033_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=e3f864&_nc_ohc=kbYDLmyJlcEAX_pShtS&_nc_ht=scontent-atl3-2.xx&oh=00_AfD90NeI7evbICEu8eExSkxDtY5-dKJemZ-tpmT9RIrVTg&oe=641BAB5C", Null);

 insert into person(person_id, first_name, middle_name, last_name) values
	(1, "Jawnathan", "Sebastian", "Sellers"),
    (2, "Ginny", Null, "Gogarty"),
    (3, "Kevin", Null, "Poynter");

 insert into person_role(person_role_id, person_role_name) values
	(1, "Guitarist"),
    (2, "Pianist"),
    (3, "Singer"),
    (4, "Dummer");

insert into group_person(person_id, group_id, person_role_id) values
	(1,2,3),
    (1,1,3),
    (2,2,1),
    (3,2,2);
 
insert into person_gig_role(person_id, gig_id, person_role_id) values
	(1,2,1),
    (1,1,2),
    (2,1,1),
    (3,2,3),
    (2,3,2),
    (3,3,1);

insert into video(video_id, video_url, video_name) values
	(1, "https://joy1.videvo.net/videvo_files/video/free/2013-09/large_watermarked/DiscoLights2Videvo_preview.mp4", "stage lights"),
    (2, "https://joy1.videvo.net/videvo_files/video/free/video0455/large_watermarked/_import_609113a1be0e89.39394997_preview.mp4", "typing on computer"),
    (3, "https://joy1.videvo.net/videvo_files/video/free/video0461/large_watermarked/_import_60e0167b4c3a96.14254367_preview.mp4", "Grass");

insert into group_video(group_id, video_id) values
	(1,1),
    (2,2),
    (3,3);

insert into album(album_id, album_name, release_year, photo_url, buy_album_url) values
	(1, "Fake album", 2021, Null, Null);

insert into song(song_id, song_name, listen_to_song, photo_url, buy_song_url, album_id, group_id) values
	(1, "A 5 to 9", "https://open.spotify.com/track/54j0KYUOyh093AEWRih3ff", Null, Null, Null, 1),
    (2, "Wind but Never Sails", "https://open.spotify.com/album/37dmOkAhhqwgbRWtE1qaIs", Null, Null, Null, 1),
    (3, "Fake Song", "https://open.spotify.com/album/37dmOkAhhqwgbRWtE1qaIs", Null, Null, 1, 3);

insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

-- passwords are set to "P@ssw0rd!"
insert into app_user (username, first_name, last_name, email, password_hash, enabled)
    values
    ('jawnathan91', "Jawnathan", "Sellers", "jawanathansebastiansellers@gmail.com", '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
    ('sally91', "Sally", "Jones", 'sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1);

insert into app_user_role
    values
    (1, 2),
    (2, 1);
    