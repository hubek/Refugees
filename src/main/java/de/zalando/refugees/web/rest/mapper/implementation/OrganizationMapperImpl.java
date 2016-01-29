package de.zalando.refugees.web.rest.mapper.implementation;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.repository.OrganizationTypeRepository;
import de.zalando.refugees.web.rest.dto.OrganizationDTO;
import de.zalando.refugees.web.rest.mapper.OrganizationMapper;

import javax.inject.Inject;


/**
 * Mapper for the entity Organization and its DTO OrganizationDTO.
 */
public class OrganizationMapperImpl implements OrganizationMapper
{
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
		dto.setTypeId( organization.getType().getId() );

		return dto;
	}

	@Override
	public Organization organizationDTOToOrganization( OrganizationDTO organizationDTO )
	{
		Organization organization = new Organization();
		OrganizationType type = organizationTypeRepository.findOne( organizationDTO.getTypeId() );

		organization.setId( organizationDTO.getId() );
		organization.setAddress( organizationDTO.getAddress() );
		organization.setEmail( organizationDTO.getEmail() );
		organization.setName( organizationDTO.getName() );
		organization.setPhone( organizationDTO.getPhone() );
		organization.setType( type );

		return organization;
	}

}
