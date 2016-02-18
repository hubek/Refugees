package de.zalando.refugees.domain.util;

import java.util.Comparator;
import de.zalando.refugees.domain.Demand;

public class DemandComparator implements Comparator< Demand >
{	
	@Override
	public int compare( Demand firstDemand, Demand secondDemand )
	{
		if( firstDemand.getDistance() >= secondDemand.getDistance() )
		{
			return 1;
		}
		
		return -1;
	}
}
