package de.zalando.refugees.web.rest.mapper.implementation;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.repository.OrganizationRepository;
import de.zalando.refugees.web.rest.dto.BranchDTO;
import de.zalando.refugees.web.rest.mapper.BranchMapper;

import javax.inject.Inject;

/**
 * Mapper for the entity Branch and its DTO BranchDTO.
 */
public class BranchMapperImpl implements BranchMapper
{

	@Inject
	private OrganizationRepository organizationRepository;

	@Override
	public BranchDTO branchToBranchDTO( Branch branch )
	{
		BranchDTO dto = new BranchDTO();

		dto.setAddress( branch.getAddress() );
		dto.setEmail( branch.getEmail() );
		dto.setId( branch.getId() );
		dto.setPhone( branch.getPhone() );
		dto.setOrganizationId( branch.getOrganization().getId() );

		return dto;
	}

	@Override
	public Branch branchDTOToBranch( BranchDTO branchDTO )
	{
		Branch branch = new Branch();
		Organization org = organizationRepository.findOne( branchDTO.getOrganizationId() );

		branch.setAddress( branchDTO.getAddress() );
		branch.setEmail( branchDTO.getEmail() );
		branch.setId( branchDTO.getId() );
		branch.setPhone( branchDTO.getPhone() );
		branch.setOrganization( org );

		return branch;
	}
}
