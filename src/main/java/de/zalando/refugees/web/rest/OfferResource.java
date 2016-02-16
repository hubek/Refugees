package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.maps.model.LatLng;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.domain.Offer;
import de.zalando.refugees.service.OfferService;
import de.zalando.refugees.web.rest.util.HeaderUtil;
import de.zalando.refugees.web.rest.util.PaginationUtil;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.dto.OfferDTO;
import de.zalando.refugees.web.rest.mapper.OfferMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Offer.
 */
@RestController
@RequestMapping( "/api" )
public class OfferResource
{

	private final Logger log = LoggerFactory.getLogger( OfferResource.class );

	@Inject
	private OfferService offerService;

	@Inject
	private OfferMapper offerMapper;

	/**
	 * POST /offers -> Create a new offer.
	 */
	@RequestMapping( value = "/offers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< OfferDTO > createOffer( @RequestBody OfferDTO offerDTO ) throws URISyntaxException
	{
		log.debug( "REST request to save Offer : {}", offerDTO );
		if ( offerDTO.getId() != null )
		{
			return ResponseEntity.badRequest().headers( HeaderUtil.createFailureAlert( "offer", "idexists", "A new offer cannot already have an ID" ) ).body( null );
		}
		OfferDTO result = offerService.save( offerDTO );
		return ResponseEntity.created( new URI( "/api/offers/"
				+ result.getId() ) ).headers( HeaderUtil.createEntityCreationAlert( "offer", result.getId().toString() ) ).body( result );
	}

	/**
	 * PUT /offers -> Updates an existing offer.
	 */
	@RequestMapping( value = "/offers", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< OfferDTO > updateOffer( @RequestBody OfferDTO offerDTO ) throws URISyntaxException
	{
		log.debug( "REST request to update Offer : {}", offerDTO );
		if ( offerDTO.getId() == null )
		{
			return createOffer( offerDTO );
		}
		OfferDTO result = offerService.save( offerDTO );
		return ResponseEntity.ok().headers( HeaderUtil.createEntityUpdateAlert( "offer", offerDTO.getId().toString() ) ).body( result );
	}

	/**
	 * GET /offers -> get all the offers.
	 */
	@RequestMapping( value = "/offers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	@Transactional( readOnly = true )
	public ResponseEntity< List< OfferDTO > > getAllOffers( Pageable pageable ) throws URISyntaxException
	{
		log.debug( "REST request to get a page of Offers" );
		Page< Offer > page = offerService.findAll( pageable );
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( page, "/api/offers" );
		return new ResponseEntity< >( page.getContent().stream().map( offerMapper::offerToOfferDTO ).collect( Collectors.toCollection( LinkedList::new ) ), headers, HttpStatus.OK );
	}

	/**
	 * GET /offers/:id -> get the "id" offer.
	 */
	@RequestMapping( value = "/offers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< OfferDTO > getOffer( @PathVariable Long id )
	{
		log.debug( "REST request to get Offer : {}", id );
		OfferDTO offerDTO = offerService.findOne( id );
		return Optional.ofNullable( offerDTO ).map( result -> new ResponseEntity< >( result, HttpStatus.OK ) ).orElse( new ResponseEntity< >( HttpStatus.NOT_FOUND ) );
	}

	/**
	 * DELETE /offers/:id -> delete the "id" offer.
	 */
	@RequestMapping(	value = "/offers/{id}", method = RequestMethod.DELETE,
						produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< Void > deleteOffer( @PathVariable Long id )
	{
		log.debug( "REST request to delete Offer : {}", id );
		offerService.delete( id );
		return ResponseEntity.ok().headers( HeaderUtil.createEntityDeletionAlert( "offer", id.toString() ) ).build();
	}

	/**
	 * GET /offers/filter -> get all the offers by filter.
	 */
	@RequestMapping( value = "/offers/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	@Transactional( readOnly = true )
	public ResponseEntity< List< OfferDTO > > getAllOffersByFilter( Pageable pageable, OfferDTO offerDTO )
			throws URISyntaxException
	{
		log.debug( "REST request to get a page of Offers" );

		Page< Offer > page = offerService.findAllByFilter( pageable, offerMapper.offerDTOToOffer( offerDTO ) );

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( page, "/api/offers/filter" );

		return new ResponseEntity< >( page.getContent().stream().map( offerMapper::offerToOfferDTO ).collect( Collectors.toCollection( LinkedList::new ) ), headers, HttpStatus.OK );
	}
}
