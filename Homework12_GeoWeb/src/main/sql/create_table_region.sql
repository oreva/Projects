-- Table: region

--DROP TABLE IF EXISTS region;

CREATE TABLE region
(
  id serial NOT NULL,
  name character varying(100),
  country_id bigint NOT NULL,
  CONSTRAINT region_pkey PRIMARY KEY (id),
  CONSTRAINT country_fkey FOREIGN KEY (country_id)
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE region
  OWNER TO study;

CREATE INDEX region_country_index ON region (country_id);