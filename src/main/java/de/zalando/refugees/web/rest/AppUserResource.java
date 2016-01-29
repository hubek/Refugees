package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.AppUser;
import de.zalando.refugees.service.AppUserService;
import de.zalando.refugees.web.rest.util.HeaderUtil;
import de.zalando.refugees.web.rest.dto.AppUserDTO;
import de.zalando.refugees.web.rest.mapper.AppUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing AppUser.
 */
@RestController
@RequestMapping( "/api" )
public class AppUserResource
{

	private final Logger log = LoggerFactory.getLogger( AppUserResource.class );

	@Inject
	private AppUserService appUserService;

	@Inject
	private AppUserMapper appUserMapper;

	/**
	 * POST /appUsers -> Create a new appUser.
	 */
	@RequestMapping( value = "/appUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< AppUserDTO > createAppUser( @RequestBody AppUserDTO appUserDTO ) throws URISyntaxException
	{
		log.debug( "REST request to save AppUser : {}", appUserDTO );
		if ( appUserDTO.getId() != null )
		{
			return ResponseEntity.badRequest().headers( HeaderUtil.createFailureAlert( "appUser", "idexists", "A new appUser cannot already have an ID" ) ).body( null );
		}
		AppUserDTO result = appUserService.save( appUserDTO );
		return ResponseEntity.created( new URI( "/api/appUsers/"
				+ result.getUserId() ) ).headers( HeaderUtil.createEntityCreationAlert( "appUser", result.getUserId().toString() ) ).body( result );
	}

	/**
	 * PUT /appUsers -> Updates an existing appUser.
	 */
	@RequestMapping( value = "/appUsers", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< AppUserDTO > updateAppUser( @RequestBody AppUserDTO appUserDTO ) throws URISyntaxException
	{
		log.debug( "REST request to update AppUser : {}", appUserDTO );
		if ( appUserDTO.getId() == null )
		{
			return createAppUser( appUserDTO );
		}
		AppUserDTO result = appUserService.save( appUserDTO );
		return ResponseEntity.ok().headers( HeaderUtil.createEntityUpdateAlert( "appUser", appUserDTO.getId().toString() ) ).body( result );
	}

	/**
	 * GET /appUsers -> get all the appUsers.
	 */
	@RequestMapping( value = "/appUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	@Transactional( readOnly = true )
	public List< AppUserDTO > getAllAppUsers()
	{
		log.debug( "REST request to get all AppUsers" );
		return appUserService.findAll();
	}

	/**
	 * GET /appUsers/:id -> get the "id" appUser.
	 */
	@RequestMapping( value = "/appUsers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< AppUserDTO > getAppUser( @PathVariable Long id )
	{
		log.debug( "REST request to get AppUser : {}", id );
		AppUserDTO appUserDTO = appUserService.findOne( id );
		return Optional.ofNullable( appUserDTO ).map( result -> new ResponseEntity< >( result, HttpStatus.OK ) ).orElse( new ResponseEntity< >( HttpStatus.NOT_FOUND ) );
	}

	/**
	 * DELETE /appUsers/:id -> delete the "id" appUser.
	 */
	@RequestMapping(	value = "/appUsers/{id}", method = RequestMethod.DELETE,
						produces = MediaType.APPLICATION_JSON_VALUE )
	@Timed
	public ResponseEntity< Void > deleteAppUser( @PathVariable Long id )
	{
		log.debug( "REST request to delete AppUser : {}", id );
		appUserService.delete( id );
		return ResponseEntity.ok().headers( HeaderUtil.createEntityDeletionAlert( "appUser", id.toString() ) ).build();
	}
}
