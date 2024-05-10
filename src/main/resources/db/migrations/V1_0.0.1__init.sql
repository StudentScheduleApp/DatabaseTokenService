CREATE TABLE IF NOT EXISTS refresh_tokens
(
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    token character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT refresh_tokens_pkey PRIMARY KEY (email)
)

TABLESPACE pg_default;