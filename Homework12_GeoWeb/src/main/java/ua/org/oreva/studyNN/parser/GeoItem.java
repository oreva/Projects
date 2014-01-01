package ua.org.oreva.studyNN.parser;

import ua.org.oreva.studyNN.exception.GeoItemFormatException;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/9/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeoItem {
	public static final String COUNTRY_CODE = "countryCode";
	public static final String POST_CODE = "postCode";
	public static final String CITY = "city";
	public static final String REGION1 = "region1";
	public static final String POST_CODE1 = "postCode1";
	public static final String REGION2 = "region2";
	public static final String POST_CODE2 = "postCode2";
	public static final String REGION3 = "region3";
	public static final String POST_CODE3 = "postCode3";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String ACCURACY = "accuracy";

	private static final String[] properties = {COUNTRY_CODE, POST_CODE, CITY, REGION1, POST_CODE1,
			REGION2, POST_CODE2, REGION3, POST_CODE3, LATITUDE, LONGITUDE, ACCURACY};

	private HashMap<String, String> dataMap;
	/**
	 *
	 * @param values is an array of tab-delimited values from the file
	 */
	public GeoItem(String[] values) throws GeoItemFormatException {
		dataMap = new HashMap<String, String>();
		if (values.length > properties.length) {
			throw new GeoItemFormatException();
		}
		for (int i = 0; i < values.length; i++) {
			if (!(values[i]).isEmpty()) {
				dataMap.put(properties[i], values[i]);
			}
		}
	}

	public boolean hasProperty(String property) {
		return dataMap.containsKey(property);
	}

	public String getProperty(String property) {
		return dataMap.get(property);
	}
}
