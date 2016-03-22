
CREATE TABLE media (
                id_media INTEGER NOT NULL,
                url_media VARCHAR(150) NOT NULL,
                created_at_media TIMESTAMP NOT NULL,
                descripcion_media VARCHAR(200) NOT NULL,
                views_media INTEGER NOT NULL,
                likes_media INTEGER NOT NULL,
                name_media VARCHAR(150) NOT NULL,
                tags_media VARCHAR(100) NOT NULL,
                CONSTRAINT id_media PRIMARY KEY (id_media)
);


CREATE TABLE comments (
                id_comments INTEGER NOT NULL,
                username_comments VARCHAR(150) NOT NULL,
                text_comments VARCHAR(300) NOT NULL,
                created_at_comments TIMESTAMP NOT NULL,
                id_media INTEGER NOT NULL,
                CONSTRAINT id_comments PRIMARY KEY (id_comments)
);


CREATE TABLE app_user (
                id_app_user INTEGER NOT NULL,
                lastname_app_user VARCHAR(150) NOT NULL,
                name_app_user VARCHAR(150) NOT NULL,
                birthday_app_user DATE NOT NULL,
                username_app_user VARCHAR(150) NOT NULL,
                password_app_user VARCHAR(20) NOT NULL,
                email_app_user VARCHAR(320) NOT NULL,
                CONSTRAINT id_app_user PRIMARY KEY (id_app_user)
);


CREATE TABLE user_media (
                id_media INTEGER NOT NULL,
                id_app_user INTEGER NOT NULL,
                CONSTRAINT id_user_media PRIMARY KEY (id_media, id_app_user)
);


ALTER TABLE user_media ADD CONSTRAINT media_user_media_fk
FOREIGN KEY (id_media)
REFERENCES media (id_media)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE comments ADD CONSTRAINT media_comments_fk
FOREIGN KEY (id_media)
REFERENCES media (id_media)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE user_media ADD CONSTRAINT app_user_user_media_fk
FOREIGN KEY (id_app_user)
REFERENCES app_user (id_app_user)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;
