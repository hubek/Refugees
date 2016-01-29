package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Season;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Season entity.
 */
public interface SeasonRepository extends JpaRepository<Season,Long> {

}
