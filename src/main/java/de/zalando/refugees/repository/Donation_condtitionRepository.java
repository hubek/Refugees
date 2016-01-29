package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Donation_condtition;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Donation_condtition entity.
 */
public interface Donation_condtitionRepository extends JpaRepository<Donation_condtition,Long> {

}
