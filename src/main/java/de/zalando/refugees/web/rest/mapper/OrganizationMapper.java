package de.zalando.refugees.web.rest.mapper;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.web.rest.dto.OrganizationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Organization and its DTO OrganizationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganizationMapper {

    @Mapping(source = "type.id", target = "typeId")
    OrganizationDTO organizationToOrganizationDTO(Organization organization);

    @Mapping(source = "typeId", target = "type")
    Organization organizationDTOToOrganization(OrganizationDTO organizationDTO);

    default OrganizationType organizationTypeFromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganizationType organizationType = new OrganizationType();
        organizationType.setId(id);
        return organizationType;
    }
}
