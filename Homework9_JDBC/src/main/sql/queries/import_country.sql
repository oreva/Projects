/* Variables: country_code */
insert into country (iso_code, name)
values (?, ?)
/*where not exists
(select 1
from country c
where c.iso_code = :country_code) */