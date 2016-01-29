package de.zalando.refugees.repository;

import de.zalando.refugees.domain.AppUser;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AppUser entity.
 */
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

}
