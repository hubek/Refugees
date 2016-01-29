package de.zalando.refugees.repository;

import de.zalando.refugees.domain.DonationCondition;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DonationCondition entity.
 */
public interface DonationConditionRepository extends JpaRepository<DonationCondition,Long> {

}
