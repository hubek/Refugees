package de.zalando.refugees.web.rest.mapper;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.web.rest.dto.BranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Branch and its DTO BranchDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BranchMapper {

    @Mapping(source = "organization.id", target = "organizationId")
    BranchDTO branchToBranchDTO(Branch branch);

    @Mapping(source = "organizationId", target = "organization")
    Branch branchDTOToBranch(BranchDTO branchDTO);

    default Organization organizationFromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
