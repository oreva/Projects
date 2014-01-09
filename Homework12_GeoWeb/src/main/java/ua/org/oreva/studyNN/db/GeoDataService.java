package ua.org.oreva.studyNN.db;

import ua.org.oreva.studyNN.entities.City;
import ua.org.oreva.studyNN.entities.Country;
import ua.org.oreva.studyNN.entities.Postcode;
import ua.org.oreva.studyNN.entities.Region;
import ua.org.oreva.studyNN.util.SmallContentReader;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/14/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeoDataService {
	private static GeoDataService instance;

	private ServletContext currentContext;

	private GeoDataService(){}

	public static GeoDataService instance() {
		if (instance == null) {
			instance = new GeoDataService();
		}
		return instance;
	}

	public ServletContext getCurrentContext() {
		return currentContext;
	}

	public void setCurrentContext(ServletContext context) {
		currentContext = context;
	}

	public Country loadCountry(String countryCode) {
		Connection connection = null;
		try {
			connection = DBManager.instance().getConnection();
			Statement statement = connection.createStatement();
			String sql = "select id from country where iso_code = '" + countryCode + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			Country country = null;
			while (resultSet.next()) {
				country = new Country(resultSet.getInt(1), countryCode);
				break;
			}
			return country;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception o) {
				//Empty block
			}

		}
	}

	public Region loadRegion(String regionName, int countryId) {
		Connection connection = null;
		try {
			connection = DBManager.instance().getConnection();
			Statement statement = connection.createStatement();
			String sql = "select id from region where name = '" + regionName + "' and country_id = " + countryId;
			ResultSet resultSet = statement.executeQuery(sql);
			Region region = null;
			while (resultSet.next()) {
				region = new Region(resultSet.getInt(1), regionName, countryId);
				break;
			}
			return region;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception o) {
				//Empty block
			}
		}
	}

	public City loadCity(String cityName, Integer countryId, Integer regionId) {
		Connection connection = null;
		try {
			connection = DBManager.instance().getConnection();
			Statement statement = connection.createStatement();
			String sql = "select id from city where name = '" + cityName + "' and country_id = " + countryId;
			if (regionId != null) {
				sql += " and region_id = " + regionId;
			} else {
				sql += " and region_id is null";
			}
			ResultSet resultSet = statement.executeQuery(sql);
			City city = null;
			while (resultSet.next()) {
				city = new City(resultSet.getInt(1), cityName, countryId);
				if (regionId != null) {
					city.setRegionId(regionId);
				}
				break;
			}
			return city;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception o) {
				//Empty block
			}
		}
	}

	public Postcode loadPostcode(String value, Integer countryId, Integer regionId, Integer cityId) {
		Connection connection = null;
		try {
			connection = DBManager.instance().getConnection();
			Statement statement = connection.createStatement();
			String sql = "select id, latitude, longitude, accuracy from postcode where value = '" + value + "' and country_id = " + countryId;
			if (regionId != null) {
				sql += " and region_id = " + regionId;
			} else {
				sql += " and region_id is null";
			}
			if (cityId != null) {
				sql += " and city_id = " + cityId;
			} else {
				sql += " and city_id is null";
			}

			ResultSet resultSet = statement.executeQuery(sql);
			Postcode postcode = null;
			while (resultSet.next()) {
				postcode = new Postcode(resultSet.getInt(1),
						value,
						resultSet.getDouble(2),
						resultSet.getDouble(3),
						resultSet.getDouble(4),
						countryId);
				if (regionId != null) {
					postcode.setRegionId(regionId);
				}
				if (cityId != null) {
					postcode.setCityId(cityId);
				}
				break;
			}
			return postcode;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception o) {
				//Empty block
			}
		}
	}

	public void importCountry(String countryCode) {
		try {
			InputStream importSQL = getCurrentContext().getResourceAsStream("/WEB-INF/classes/queries/import_country.sql");
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new InputStreamReader(importSQL)));
			String sql = reader.readAll();
			reader.close();

			Connection connection = DBManager.instance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, countryCode);
			statement.setString(2, countryCode);
			statement.execute();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void importRegion(String regionName, int countryId) {
		try {
			InputStream importSQL = getCurrentContext().getResourceAsStream("/WEB-INF/classes/queries/import_region.sql");
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new InputStreamReader(importSQL)));
			String sql = reader.readAll();
			reader.close();

			Connection connection = DBManager.instance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, regionName);
			statement.setInt(2, countryId);
			statement.execute();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void importCity(String cityName, Integer countryId, Integer regionId) {
		try {
			InputStream importSQL = getCurrentContext().getResourceAsStream("/WEB-INF/classes/queries/import_city.sql");
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new InputStreamReader(importSQL)));
			String sql = reader.readAll();
			reader.close();

			Connection connection = DBManager.instance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cityName);
			statement.setObject(2, countryId);
			statement.setObject(3, regionId);
			statement.execute();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 * @param postcode
	 * @param latitude
	 * @param longitude
	 * @param accuracy
	 * @param countryId
	 * @param regionId
	 * @param cityId
	 *
	 * Wrapper types used here to be able to set null instead of the values
	 */
	protected void importPostcode(String postcode, Double latitude, Double longitude, Double accuracy,
	                              Integer countryId, Integer regionId, Integer cityId) {
		try {
			InputStream importSQL = getCurrentContext().getResourceAsStream("/WEB-INF/classes/queries/import_postcode.sql");
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new InputStreamReader(importSQL)));
			String sql = reader.readAll();
			reader.close();

			Connection connection = DBManager.instance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, postcode);
			statement.setObject(2, latitude);
			statement.setObject(3, longitude);
			statement.setObject(4, accuracy);
			statement.setObject(5, countryId);
			statement.setObject(6, regionId);
			statement.setObject(7, cityId);
			statement.setObject(8, countryId);
			statement.setString(9, postcode);
			statement.setObject(10, countryId);
			statement.execute();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ResultSet searchCountries(String code, String name) {
		Connection connection = null;
		try {
			connection = DBManager.instance().getConnection();
			Statement statement = connection.createStatement();
			StringBuilder sql = new StringBuilder("select id, iso_code from country where 1 = 1");
			if (code != null) {
				sql.append(" and iso_code like '%" + code + "%'");
			}
			if (name != null) {
				sql.append(" and name like '%" + name + "%'");
			}
			ResultSet resultSet = statement.executeQuery(sql.toString());
			return resultSet;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception o) {
				//Empty block
			}
		}
	}
}
