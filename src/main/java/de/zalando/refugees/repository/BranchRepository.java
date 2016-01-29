package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Branch;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Branch entity.
 */
public interface BranchRepository extends JpaRepository<Branch,Long> {

}
