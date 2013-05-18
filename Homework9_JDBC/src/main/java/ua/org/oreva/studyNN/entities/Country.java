package ua.org.oreva.studyNN.entities;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/12/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Country {
	private int id;
	private String isoCode;

	public int id() {
		return id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public String isoCode() {
		return isoCode;
	}

	public void setIsoCode(String newIsoCode) {
		isoCode = newIsoCode;
	}

	public Country(){}

	public Country(String isoCode) {
		this.isoCode = isoCode;
	}

	public Country(int id, String isoCode) {
		this(isoCode);
		this.id = id;
	}
}
