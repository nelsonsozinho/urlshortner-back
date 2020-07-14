CREATE TABLE public.url
(
    id bigint NOT NULL,
    original_url text COLLATE pg_catalog."default",
    creation_date timestamp,
    expiration_date timestamp,
    id_user bigint NOT NULL,
    CONSTRAINT url_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user
(
    id bigint NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    password text NOT NULL,
    creation_date timestamp,
    last_login timestamp,
    username varchar(50) NOT NULL,
    enabled boolean,
    account_non_expired boolean,
    credentials_non_expired boolean,
    account_non_locked boolean,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.role
(
    id bigint NOT NULL,
    name varchar(100),
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_role
(
    id_user bigint NOT NULL,
    id_role bigint NOT NULL
);

ALTER TABLE public.url
  ADD CONSTRAINT FK_user_url_user
  FOREIGN KEY(id_user) REFERENCES public.user(id);


ALTER TABLE public.user_role
  ADD CONSTRAINT FK_user_role_user
  FOREIGN KEY (id_user) REFERENCES public.user(id);

ALTER TABLE public.user_role
  ADD CONSTRAINT FK_user_role_role
  FOREIGN KEY (id_role) REFERENCES public.role(id);



CREATE SEQUENCE public."seq-user" INCREMENT 10 START 1 MINVALUE 0 MAXVALUE 9223372036854775807 CACHE 1;
ALTER SEQUENCE public."seq-user" OWNER TO shortener;

CREATE SEQUENCE public."seq-url" INCREMENT 10 START 1 MINVALUE 0 MAXVALUE 9223372036854775807 CACHE 1;
ALTER SEQUENCE public."seq-url" OWNER TO shortener;

INSERT INTO public.role(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO public.role(id, name) VALUES(2, 'ROLE_PM');
INSERT INTO public.role(id, name) VALUES(3, 'ROLE_ADMIN');

