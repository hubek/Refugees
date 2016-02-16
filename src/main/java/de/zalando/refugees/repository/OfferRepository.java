package de.zalando.refugees.repository;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.domain.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Offer entity.
 */
public interface OfferRepository extends JpaRepository< Offer, Long >, JpaSpecificationExecutor
{
}
