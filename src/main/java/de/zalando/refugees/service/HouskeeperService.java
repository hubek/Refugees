package de.zalando.refugees.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.zalando.refugees.domain.Offer;
import de.zalando.refugees.repository.OfferRepository;
import de.zalando.refugees.web.rest.AccountResource;

@Component
public class HouskeeperService
{
	private final Logger log = LoggerFactory.getLogger( HouskeeperService.class );

	@Inject
	private OfferRepository offerRepository;
	
	@Inject
	private MailService mailService;

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

}
