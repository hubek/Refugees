package de.zalando.refugees.repository;

import de.zalando.refugees.domain.OrganizationType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrganizationType entity.
 */
public interface OrganizationTypeRepository extends JpaRepository<OrganizationType,Long> {

}
