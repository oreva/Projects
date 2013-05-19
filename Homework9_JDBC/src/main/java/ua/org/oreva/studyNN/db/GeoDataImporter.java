package ua.org.oreva.studyNN.db;

import ua.org.oreva.studyNN.entities.City;
import ua.org.oreva.studyNN.entities.Country;
import ua.org.oreva.studyNN.entities.Postcode;
import ua.org.oreva.studyNN.entities.Region;
import ua.org.oreva.studyNN.exception.GeoItemFormatException;
import ua.org.oreva.studyNN.parser.GeoFileParser;
import ua.org.oreva.studyNN.parser.GeoItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/12/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeoDataImporter {

	public void importGeoData(String filePath) throws IOException {
		GeoFileParser parser = new GeoFileParser(new BufferedReader(new FileReader(filePath)));
		GeoItem item;
		try {
			Country country = null;
			while ((item = parser.parseNextLineAsGeoItem()) != null) {
				if (country == null) {
					country = importCountry(item);
				}
				importGeoItem(item, country);
			}
		} catch (GeoItemFormatException e) {
			throw new RuntimeException(e);
		}
		parser.close();
	}

	public Country importCountry(GeoItem item) {
		String countryCode = item.getProperty(GeoItem.COUNTRY_CODE);
		Country country = GeoDataService.instance().loadCountry(countryCode);
		if (country == null) {
			GeoDataService.instance().importCountry(countryCode);
			country = GeoDataService.instance().loadCountry(countryCode);
		}
		return country;
	}

	protected void importGeoItem(GeoItem item, Country country) {
		Region region = importRegion(item, country);
		City city = importCity(item, country, region);
	}

	public Region importRegion(GeoItem item, Country country) {
		String regionName = null;
		String regionPostCode = null;
		if (item.hasProperty(GeoItem.REGION1)) {
			regionName = item.getProperty(GeoItem.REGION1);
			regionPostCode = item.getProperty(GeoItem.POST_CODE1);
		} else if (item.hasProperty(GeoItem.REGION2)) {
			regionName = item.getProperty(GeoItem.REGION2);
			regionPostCode = item.getProperty(GeoItem.POST_CODE2);
		} else if (item.hasProperty(GeoItem.REGION3)) {
			regionName = item.getProperty(GeoItem.REGION3);
			regionPostCode = item.getProperty(GeoItem.POST_CODE3);
		}
		Region region = GeoDataService.instance().loadRegion(regionName, country.id());
		if (region == null) {
			GeoDataService.instance().importRegion(regionName, country.id());
			region = GeoDataService.instance().loadRegion(regionName, country.id());
		}
		if (regionPostCode != null) {
			importPostcode(item, regionPostCode, country, region, null);
		}
		return region;
	}

	public City importCity(GeoItem item, Country country, Region region) {
		String cityName = item.getProperty(GeoItem.CITY);
		String cityPostCode = item.getProperty(GeoItem.POST_CODE);

		City city = GeoDataService.instance().loadCity(cityName, country.id(), region.id());
		if (city == null) {
			GeoDataService.instance().importCity(cityName, country.id(), region.id());
			city = GeoDataService.instance().loadCity(cityName, country.id(), region.id());
		}
		importPostcode(item, cityPostCode, country, region, city);
		return city;
	}


	public void importPostcode(GeoItem item,
	                               String postcodeValue,
	                               Country country,
	                               Region region,
	                               City city) {
		Integer cityId = null;
		Double lat = null;
		Double lon = null;
		Double accuracy = null;
		if (city != null) {
			cityId = city.id();
			lat = item.hasProperty(GeoItem.LATITUDE) ? Double.valueOf(item.getProperty(GeoItem.LATITUDE)) : null;
			lon = item.hasProperty(GeoItem.LONGITUDE) ? Double.valueOf(item.getProperty(GeoItem.LONGITUDE)) : null;
			accuracy = item.hasProperty(GeoItem.ACCURACY) ? Double.valueOf(item.getProperty(GeoItem.ACCURACY)) : null;
		}
		GeoDataService.instance().importPostcode(postcodeValue, lat, lon, accuracy, country.id(), region.id(), cityId);
	}
}
