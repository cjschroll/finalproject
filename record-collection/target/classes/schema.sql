DROP TABLE IF EXISTS artist_album;
DROP TABLE IF EXISTS genre_album;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS contributor;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS genre;

CREATE TABLE genre (
    genre_id int NOT NULL AUTO_INCREMENT,
    genre_name varchar(60) NOT NULL,
    PRIMARY KEY (genre_id)
);

CREATE TABLE contributor (
    contributor_id int NOT NULL AUTO_INCREMENT,
    contributor_name varchar(256) NOT NULL,
    contributor_username varchar(256) NOT NULL,
    PRIMARY KEY (contributor_id)
);

CREATE TABLE artist (
    artist_id int NOT NULL AUTO_INCREMENT,
    artist_name varchar(60) NOT NULL,
    PRIMARY KEY (artist_id)
);

CREATE TABLE album (
    album_id int NOT NULL AUTO_INCREMENT,
    album_title varchar(256) NOT NULL,
    year_released int NOT NULL,
    contributor_id int NOT NULL,
    artist_id int NOT NULL,
    PRIMARY KEY (album_id),
    FOREIGN KEY (contributor_id) REFERENCES contributor (contributor_id),
    FOREIGN KEY (artist_id) REFERENCES artist (artist_id)
);

CREATE TABLE genre_album (
    genre_id int NOT NULL,
    album_id int NOT NULL,
    UNIQUE KEY (genre_id, album_id),
    FOREIGN KEY (genre_id) REFERENCES genre (genre_id) ON DELETE CASCADE,
    FOREIGN KEY (album_id) REFERENCES album (album_id) ON DELETE CASCADE
);
