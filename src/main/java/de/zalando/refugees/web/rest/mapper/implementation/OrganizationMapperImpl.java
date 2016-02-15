package de.zalando.refugees.web.rest.mapper.implementation;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.repository.OrganizationTypeRepository;
import de.zalando.refugees.service.GeoCodingService;
import de.zalando.refugees.web.rest.dto.OrganizationDTO;
import de.zalando.refugees.web.rest.mapper.OrganizationMapper;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.maps.model.LatLng;

/**
 * Mapper for the entity Organization and its DTO OrganizationDTO.
 */
public class OrganizationMapperImpl implements OrganizationMapper
{
	@Autowired
	GeoCodingService geoCodingService;

	@Inject
	private OrganizationTypeRepository organizationTypeRepository;

	@Override
	public OrganizationDTO organizationToOrganizationDTO( Organization organization )
	{
		OrganizationDTO dto = new OrganizationDTO();

		dto.setId( organization.getId() );
		dto.setAddress( organization.getAddress() );
		dto.setEmail( organization.getEmail() );
		dto.setName( organization.getName() );
		dto.setPhone( organization.getPhone() );
		dto.setLang( organization.getLang() );
		dto.setLat( organization.getLat() );

		if ( organization.getType() != null )
		{
			dto.setTypeId( organization.getType().getId() );

		}

		return dto;
	}

	@Override
	public Organization organizationDTOToOrganization( OrganizationDTO organizationDTO )
	{
		Organization organization = new Organization();

		// get Organization type id
		OrganizationType type = null;

		if ( organizationDTO.getTypeId() != null )
		{
			type = organizationTypeRepository.findOne( organizationDTO.getTypeId() );
		}

		// set attributes
		organization.setId( organizationDTO.getId() );
		organization.setAddress( organizationDTO.getAddress() );
		organization.setEmail( organizationDTO.getEmail() );
		organization.setName( organizationDTO.getName() );
		organization.setPhone( organizationDTO.getPhone() );
		organization.setType( type );

		// get Lat Lang values
		if ( organization.getLang() == null || organization.getLat() == null )
		{
			LatLng geoPoint = geoCodingService.getGeoPoint( organization.getAddress() );
			organization.setLang( geoPoint.lng );
			organization.setLat( geoPoint.lat );
		}

		return organization;
	}

}
