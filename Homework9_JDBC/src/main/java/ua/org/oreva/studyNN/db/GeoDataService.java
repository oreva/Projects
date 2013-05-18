package ua.org.oreva.studyNN.db;

import ua.org.oreva.studyNN.entities.City;
import ua.org.oreva.studyNN.entities.Country;
import ua.org.oreva.studyNN.entities.Postcode;
import ua.org.oreva.studyNN.entities.Region;
import ua.org.oreva.studyNN.util.SmallContentReader;

import java.io.BufferedReader;
import java.io.FileReader;
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

	private GeoDataService(){}

	public static GeoDataService instance() {
		if (instance == null) {
			instance = new GeoDataService();
		}
		return instance;
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

	public City loadCity(String cityName, int countryId, int regionId) {
		Connection connection = null;
		try {
			connection = DBManager.instance().getConnection();
			Statement statement = connection.createStatement();
			String sql = "select id from city where name = '" + cityName + "' and country_id = " + countryId + " and region_id = " + regionId;
			ResultSet resultSet = statement.executeQuery(sql);
			City city = null;
			while (resultSet.next()) {
				city = new City(resultSet.getInt(1), cityName, countryId, regionId);
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
			String sql = "select id, latitude, longitude, accuracy from postcode where value = '" + value + "' and country_id = " + countryId + " and region_id = " + regionId;
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
						countryId,
						regionId,
						cityId);
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
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new FileReader("D:/Education/JavaAcademy/Projects/Homework9_JDBC/src/main/sql/queries/import_country.sql")));
			String sql = reader.readAll();
			reader.close();

			Connection connection = DBManager.instance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, countryCode);
			statement.execute();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void importRegion(String regionName, int countryId) {
		try {
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new FileReader("D:/Education/JavaAcademy/Projects/Homework9_JDBC/src/main/sql/queries/import_region.sql")));
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

	protected void importCity(String cityName, int countryId, int regionId) {
		try {
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new FileReader("D:/Education/JavaAcademy/Projects/Homework9_JDBC/src/main/sql/queries/import_city.sql")));
			String sql = reader.readAll();
			reader.close();

			Connection connection = DBManager.instance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cityName);
			statement.setInt(2, countryId);
			statement.setInt(3, regionId);
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
			SmallContentReader reader = new SmallContentReader(new BufferedReader(new FileReader("D:/Education/JavaAcademy/Projects/Homework9_JDBC/src/main/sql/queries/import_postcode.sql")));
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
			statement.execute();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}