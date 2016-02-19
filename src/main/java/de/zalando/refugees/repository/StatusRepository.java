package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Status;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Status entity.
 */
public interface StatusRepository extends JpaRepository< Status, Long >
{

	public List< Status > findByValue( String value );

}
