package de.zalando.refugees.service;

import de.zalando.refugees.domain.AppUser;
import de.zalando.refugees.domain.User;
import de.zalando.refugees.repository.AppUserRepository;
import de.zalando.refugees.repository.UserRepository;
import de.zalando.refugees.web.rest.dto.AppUserDTO;
import de.zalando.refugees.web.rest.mapper.AppUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AppUser.
 */
@Service
public class GeoCodingService
{
	String API_KEY = "AIzaSyCv_HKbYft0Ues0p3CciKfekDmchrGRGYY";

	/**
	 * Get GeoPOint from address
	 * 
	 * @param address : String
	 * @return LatLng instance representing Longitude and Latitude of the
	 *         address
	 */
	public LatLng getGeoPoint( String address )
	{
		GeoApiContext context = new GeoApiContext().setApiKey( API_KEY );
		GeocodingResult[] results;
		try
		{
			results = GeocodingApi.geocode( context, address ).await();
			return results[0].geometry.location;
		}
		catch ( Exception e )
		{
			System.out.println( " Can't get GeopPoint : " + e.getMessage() );
			return new LatLng( 0.0, 0.0 );
		}
	}

	/**
	 * Calculate the distance between 2 GEO points
	 * @param firstPoint : instance of type LatLng The first GEO point
	 * @param secondPoint : instance of type LatLng The second GEo point
	 * @return : The Distance
	 */
	public double getDistance( LatLng firstPoint, LatLng secondPoint )
	{
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians( secondPoint.lat - firstPoint.lat );
		double dLng = Math.toRadians( secondPoint.lng - firstPoint.lng );
		double a = Math.sin( dLat / 2 ) * Math.sin( dLat / 2 ) + Math.cos( Math.toRadians( firstPoint.lat ) )
				* Math.cos( Math.toRadians( secondPoint.lat ) ) * Math.sin( dLng / 2 ) * Math.sin( dLng / 2 );
		double c = 2 * Math.atan2( Math.sqrt( a ), Math.sqrt( 1 - a ) );
		double dist = earthRadius * c ;
		
		return dist;
	}
}
