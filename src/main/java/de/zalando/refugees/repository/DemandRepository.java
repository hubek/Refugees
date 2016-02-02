package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Demand;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Demand entity.
 */
public interface DemandRepository extends JpaRepository<Demand,Long> {

}
