package de.zalando.refugees.domain.util;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.maps.model.LatLng;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.service.GeoCodingService;
import de.zalando.refugees.web.rest.dto.DemandDTO;

public class DemandComparator implements Comparator< Demand >
{
	GeoCodingService geoCodingService;
	
	LatLng referencePoint ;
	
	public DemandComparator( GeoCodingService geoCodingService, LatLng referencePoint)
	{
		this.geoCodingService = geoCodingService;
		this.referencePoint = referencePoint;
	}
	
	@Override
	public int compare( Demand firstDemand, Demand secondDemand )
	{
		Double firstDistance = geoCodingService.getDistance( firstDemand.getBranch().getOrganization().getCoords(), referencePoint) ;
		Double secondDistance = geoCodingService.getDistance( secondDemand.getBranch().getOrganization().getCoords(), referencePoint) ;
		
		if( firstDistance >= secondDistance )
		{
			return 1;
		}
		
		return -1;
	}

}
