psql -f create_user.sql postgres postgres
psql -f create_db.sql postgres postgres
psql -f grant_privileges.sql postgres postgres
psql -f create_table_country.sql study00 study
psql -f create_table_region.sql study00 study
psql -f create_table_city.sql study00 study
psql -f create_table_postcode.sql study00 study