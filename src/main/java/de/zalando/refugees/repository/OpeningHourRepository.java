package de.zalando.refugees.repository;

import de.zalando.refugees.domain.OpeningHour;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OpeningHour entity.
 */
public interface OpeningHourRepository extends JpaRepository<OpeningHour,Long> {

}
