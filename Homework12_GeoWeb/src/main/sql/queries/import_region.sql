/* Variables: country_code, region_name */
/*insert into region (name, country_id)
(select :region_name as name,
  (select c.id
  from country c
  where c.iso_code = :country_code) as country_id
) */
/* Variables: region_name, country_id */
insert into region (name, country_id)
values (?, ?)