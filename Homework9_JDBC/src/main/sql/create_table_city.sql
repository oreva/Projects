-- Table: city

-- DROP TABLE IF EXISTS city;

CREATE TABLE city
(
  id serial NOT NULL,
  name character varying(180),
  country_id bigint NOT NULL,
  region_id bigint NOT NULL,
  CONSTRAINT city_pkey PRIMARY KEY (id),
  CONSTRAINT country_fkey FOREIGN KEY (country_id)
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT region_fkey FOREIGN KEY (region_id)
      REFERENCES region (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE city
  OWNER TO study;

CREATE INDEX city_country_index ON city (country_id);
CREATE INDEX city_region_index ON city (region_id);