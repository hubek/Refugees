package de.zalando.refugees.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.domain.Offer;
import de.zalando.refugees.domain.Status;
import de.zalando.refugees.repository.DemandRepository;
import de.zalando.refugees.repository.OfferRepository;
import de.zalando.refugees.repository.StatusRepository;
import de.zalando.refugees.web.rest.AccountResource;

@Component
public class HouskeeperService
{
	private final Logger log = LoggerFactory.getLogger( HouskeeperService.class );

	@Inject
	private OfferRepository offerRepository;
	
	@Inject
	private DemandRepository demandRepository;
	
	@Inject
	private MailService mailService;
	
	@Inject
	private StatusRepository statusRepository;

	/**
	 * Clean up the expired offers
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void cleanUpOffers()
	{
		ZonedDateTime currentTime = ZonedDateTime.now();

		List< Offer > expiredOffers = offerRepository.findByExpirationLessThan( currentTime );

		for ( Offer offer : expiredOffers )
		{
			offerRepository.delete( offer.getId() );
			log.warn( "Offer {} Has been expired and deleted ", offer.getId() );
		}

		offerRepository.flush();
	}
	
	/**
	 * Deactivate the expired demands
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void deactivateDemands()
	{
		// get deactivate status
		Status deactivatedStatus = statusRepository.findByValue( "DEACTIVATED" ).get( 0 );
		
		// get last week time 
		ZonedDateTime currentTime = ZonedDateTime.now();
		currentTime = currentTime.minusDays( 7 );

		// get expired demands
		List< Demand > expiredDemands = demandRepository.findByCreatedLessThan( currentTime );

		// deactivate them
		for ( Demand demand : expiredDemands )
		{
			demand.setStatus( deactivatedStatus );
			
			demandRepository.save( demand );
			
			sendDemandActivationEmail( demand );
			
			log.warn( "demand {} Has been expired and deactivated ", demand.getId() );
		}

		demandRepository.flush();
	}
	
	
	/**
	 * Send activation email to Demand owner
	 * @param demand
	 */
	private void sendDemandActivationEmail( Demand demand)
	{
		String email = demand.getBranch().getEmail();
		Long demandId = demand.getId();
		String baseUrl = "here goes the api url";
		
		mailService.sendDemandActivationEmail( email, demandId, baseUrl );
	}

}
