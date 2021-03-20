
CREATE TABLE athorities
(
    id integer PRIMARY KEY,
    role character varying(255)
);


CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    email character varying(255),
    enabled boolean NOT NULL,
    name character varying(255),
    password character varying(255),
    authority_id integer,
 --   CONSTRAINT user_pk  PRIMARY KEY (id),
    CONSTRAINT authority_fk FOREIGN KEY (authority_id) references athorities(id)
);




CREATE TABLE posts
(
    id SERIAL PRIMARY KEY,
    created timestamp without time zone,
    description character varying(255),
    name character varying(255),
    topic_id integer,
    user_id integer,
 --   CONSTRAINT post_pk  PRIMARY KEY (id),
    CONSTRAINT topic_fk FOREIGN KEY (topic_id) references posts(id),
    CONSTRAINT user_fk FOREIGN KEY (user_id) references users(id)
);
