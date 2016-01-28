package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Size;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Size entity.
 */
public interface SizeRepository extends JpaRepository<Size,Long> {

}
