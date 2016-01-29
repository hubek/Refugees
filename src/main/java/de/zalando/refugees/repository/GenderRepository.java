package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Gender;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Gender entity.
 */
public interface GenderRepository extends JpaRepository<Gender,Long> {

}
