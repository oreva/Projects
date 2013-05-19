/* Variables: postcode, lat, long, accuracy, country_id, region_id, city_id, country_id, postcode, country_id, region_id */
insert into postcode (value, latitude, longitude, accuracy, country_id, region_id, city_id)
(select ? as value, ? as latitude, ? as longitude, ? as accuracy, ? as country_id, ? as region_id, ? as city_id
from country c
where c.id = ?
and not exists
  (select 1
  from postcode p
  where p.value = ?
  and p.country_id = ?
  and p.region_id = ?)
)