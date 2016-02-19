package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Offer;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Offer entity.
 */
public interface OfferRepository extends JpaRepository< Offer, Long >, JpaSpecificationExecutor
{
	public List< Offer > findByExpirationLessThan( ZonedDateTime expiration );
}
