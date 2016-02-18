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
@Transactional
public class AppUserService
{

	private final Logger log = LoggerFactory.getLogger( AppUserService.class );

	@Inject
	private AppUserRepository appUserRepository;

	@Inject
	private AppUserMapper appUserMapper;

	@Inject
	private UserService userService;

	/**
	 * Create new user to link it to appUser
	 * @param appUserDTO : Instance of AppUserDto to use its info to create the new user
	 * @return : created user id
	 */
	private Long createUser( AppUserDTO appUserDTO )
	{
		User user = userService.createUserInformation( appUserDTO.getLogin(), appUserDTO.getPassword(), appUserDTO.getFirstName(), appUserDTO.getLastName(), appUserDTO.getEmail().toLowerCase(), appUserDTO.getLangKey() );
		
		return user.getId();
	}

	/**
	 * Save a appUser.
	 * 
	 * @return the persisted entity
	 */
	public AppUserDTO save( AppUserDTO appUserDTO )
	{
		log.debug( "Request to save AppUser : {}", appUserDTO );
		appUserDTO.setUserId( createUser( appUserDTO ) );
		
		AppUser appUser = appUserMapper.appUserDTOToAppUser( appUserDTO );
		//appUser.getUser().setActivated( false );
		
		appUser = appUserRepository.save( appUser );
		AppUserDTO result = appUserMapper.appUserToAppUserDTO( appUser );
		return result;
	}

	/**
	 * get all the appUsers.
	 * 
	 * @return the list of entities
	 */
	@Transactional( readOnly = true )
	public List< AppUserDTO > findAll()
	{
		log.debug( "Request to get all AppUsers" );
		List< AppUserDTO > result = appUserRepository.findAll().stream().map( appUserMapper::appUserToAppUserDTO ).collect( Collectors.toCollection( LinkedList::new ) );
		return result;
	}

	/**
	 * get one appUser by id.
	 * 
	 * @return the entity
	 */
	@Transactional( readOnly = true )
	public AppUserDTO findOne( Long id )
	{
		log.debug( "Request to get AppUser : {}", id );
		AppUser appUser = appUserRepository.findOne( id );
		AppUserDTO appUserDTO = appUserMapper.appUserToAppUserDTO( appUser );
		return appUserDTO;
	}

	/**
	 * delete the appUser by id.
	 */
	public void delete( Long id )
	{
		log.debug( "Request to delete AppUser : {}", id );
		appUserRepository.delete( id );
	}
	
	/**
	 * Activate user account
	 * @param id : user id to activate
	 */
	public void activateUser( Long id)
	{
		AppUser user = appUserRepository.findOne( id );
		user.getUser().setActivated( true );
		
		appUserRepository.save( user );
	}
}
