package ua.org.oreva.studyNN.entities;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/12/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Postcode {
	private int id;
	private String value;
	private double latitude;
	private double longitude;
	private double accuracy;
	private int countryId;
	private int regionId;
	private int cityId;

	public int id() {
		return id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public String value() {
		return value;
	}

	public void setValue(String newValue) {
		value = newValue;
	}

	public double latitude() {
		return latitude;
	}

	public void setLatitude(double newValue) {
		latitude = newValue;
	}

	public double longitude() {
		return longitude;
	}

	public void setLongitude(double newValue) {
		longitude = newValue;
	}

	public double accuracy() {
		return accuracy;
	}

	public void setAccuracy(double newValue) {
		accuracy = newValue;
	}

	public int countryId() {
		return countryId;
	}

	public void setCountryId(int newId) {
		countryId = newId;
	}

	public int regionId() {
		return regionId;
	}

	public void setRegionId(int newId) {
		regionId = newId;
	}

	public int cityId() {
		return cityId;
	}

	public void setCityId(int newId) {
		cityId = newId;
	}

	public Postcode(int id,
	                String value,
	                double lat,
	                double lon,
	                double accuracy,
	                int countryId) {
		this.id = id;
		this.value = value;
		this.latitude = lat;
		this.longitude = lon;
		this.accuracy = accuracy;
		this.countryId = countryId;
	}
}