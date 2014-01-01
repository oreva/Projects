package ua.org.oreva.studyNN.entities;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/12/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Region {
	private int id;
	private String name;
	private int countryId;

	public int id() {
		return id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public String name() {
		return name;
	}

	public void setName(String newIsoCode) {
		name = newIsoCode;
	}

	public int countryId() {
		return countryId;
	}

	public void setCountryId(int newId) {
		countryId = newId;
	}

	public Region(int id, String name, int countryId) {
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}
}
