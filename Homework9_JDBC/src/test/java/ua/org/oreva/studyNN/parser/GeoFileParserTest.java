package ua.org.oreva.studyNN.parser;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/19/13
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeoFileParserTest {
	@Test
	public void testParseNextLineAsGeoItem() throws Exception {
		String ua = "UA\t02097\tKyiv\t\t\t\t\t\t\t5\t15\t25";
		StringReader strReader = new StringReader(ua);
		GeoFileParser parser = new GeoFileParser(new BufferedReader(strReader));
		GeoItem geoItem = parser.parseNextLineAsGeoItem();
		assertNotNull(geoItem);
		assertTrue(geoItem.hasProperty(GeoItem.COUNTRY_CODE));
		assertTrue(geoItem.hasProperty(GeoItem.POST_CODE));
		assertTrue(geoItem.hasProperty(GeoItem.CITY));
		assertTrue(geoItem.hasProperty(GeoItem.LATITUDE));
		assertTrue(geoItem.hasProperty(GeoItem.LONGITUDE));
		assertTrue(geoItem.hasProperty(GeoItem.ACCURACY));
		assertFalse(geoItem.hasProperty(GeoItem.POST_CODE1));
		assertFalse(geoItem.hasProperty(GeoItem.POST_CODE2));
		assertFalse(geoItem.hasProperty(GeoItem.POST_CODE3));
		assertFalse(geoItem.hasProperty(GeoItem.REGION1));
		assertFalse(geoItem.hasProperty(GeoItem.REGION2));
		assertFalse(geoItem.hasProperty(GeoItem.REGION3));
		assertEquals("UA", geoItem.getProperty(GeoItem.COUNTRY_CODE));
		assertEquals("02097", geoItem.getProperty(GeoItem.POST_CODE));
		assertEquals("Kyiv", geoItem.getProperty(GeoItem.CITY));
		assertEquals("5", geoItem.getProperty(GeoItem.LATITUDE));
		assertEquals("15", geoItem.getProperty(GeoItem.LONGITUDE));
		assertEquals("25", geoItem.getProperty(GeoItem.ACCURACY));
	}
}
