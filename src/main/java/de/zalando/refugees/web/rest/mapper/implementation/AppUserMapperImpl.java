package de.zalando.refugees.web.rest.mapper.implementation;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.repository.BranchRepository;
import de.zalando.refugees.repository.UserRepository;
import de.zalando.refugees.web.rest.dto.AppUserDTO;
import de.zalando.refugees.web.rest.mapper.AppUserMapper;

import javax.inject.Inject;

import org.mapstruct.*;

/**
 * Mapper for the entity AppUser and its DTO AppUserDTO.
 */
public class AppUserMapperImpl implements AppUserMapper 
{
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private BranchRepository branchRepository; 

	@Override
	public AppUserDTO appUserToAppUserDTO( AppUser appUser )
	{
		AppUserDTO dto = new AppUserDTO();
		
		dto.setBranchId( appUser.getBranch().getId() );
		dto.setUserId( appUser.getUser().getId() );
		dto.setFirstName( appUser.getUser().getFirstName() );
		dto.setEmail( appUser.getUser().getEmail() );
		dto.setLastName( appUser.getUser().getLastName() );
		
		return dto;
	}

	@Override
	public AppUser appUserDTOToAppUser( AppUserDTO appUserDTO )
	{
		AppUser appUser = new AppUser();
		User user = userRepository.findOne( appUserDTO.getUserId() );
		Branch branch = branchRepository.findOne( appUserDTO.getBranchId() );
		
		appUser.setUser( user );
		appUser.setBranch( branch );
		
		return appUser;
	}

}
