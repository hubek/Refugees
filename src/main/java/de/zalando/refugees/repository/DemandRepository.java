package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Demand;

import org.springframework.data.jpa.repository.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the Demand entity.
 */
public interface DemandRepository extends JpaRepository< Demand, Long >, JpaSpecificationExecutor
{
	public List< Demand > findByCreatedLessThan( ZonedDateTime lastWeek);
}
