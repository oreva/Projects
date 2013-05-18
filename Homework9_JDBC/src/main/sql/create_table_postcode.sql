-- Table: postcode

 -- DROP TABLE IF EXISTS postcode;

CREATE TABLE postcode
(
  id serial NOT NULL,
  value character varying(20),
  latitude real,
  longitude real,
  accuracy real,
  country_id bigint,
  region_id bigint,
  city_id bigint,
  CONSTRAINT postcode_pkey PRIMARY KEY (id),
  CONSTRAINT country_fkey FOREIGN KEY (country_id)
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT region_fkey FOREIGN KEY (region_id)
      REFERENCES region (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT city_fkey FOREIGN KEY (city_id)
      REFERENCES city (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE postcode
  OWNER TO study;

CREATE INDEX postcode_country_index ON postcode (country_id);
CREATE INDEX postcode_region_index ON postcode (region_id);
CREATE INDEX postcode_city_index ON postcode (city_id);
