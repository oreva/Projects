package ua.org.oreva.studyNN.entities;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/12/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class City {
	private int id;
	private String name;
	private int countryId;
	private int regionId;

	public int id() {
		return id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public String name() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
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

	public City(int id, String name, int countryId, int regionId) {
		this.id = id;
		this.name = name;
		this.countryId = countryId;
		this.regionId = regionId;
	}
}
