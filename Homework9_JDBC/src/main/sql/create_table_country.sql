-- Table: country

--DROP TABLE IF EXISTS country;

CREATE TABLE country
(
  id serial NOT NULL,
  iso_code character varying(2),
  name character varying(100),
  CONSTRAINT country_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE country
  OWNER TO study;
