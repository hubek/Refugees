package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.OrganizationType;
import de.zalando.refugees.repository.OrganizationTypeRepository;
import de.zalando.refugees.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrganizationType.
 */
@RestController
@RequestMapping("/api")
public class OrganizationTypeResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationTypeResource.class);
        
    @Inject
    private OrganizationTypeRepository organizationTypeRepository;
    
    /**
     * POST  /organizationTypes -> Create a new organizationType.
     */
    @RequestMapping(value = "/organizationTypes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrganizationType> createOrganizationType(@RequestBody OrganizationType organizationType) throws URISyntaxException {
        log.debug("REST request to save OrganizationType : {}", organizationType);
        if (organizationType.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("organizationType", "idexists", "A new organizationType cannot already have an ID")).body(null);
        }
        OrganizationType result = organizationTypeRepository.save(organizationType);
        return ResponseEntity.created(new URI("/api/organizationTypes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("organizationType", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organizationTypes -> Updates an existing organizationType.
     */
    @RequestMapping(value = "/organizationTypes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrganizationType> updateOrganizationType(@RequestBody OrganizationType organizationType) throws URISyntaxException {
        log.debug("REST request to update OrganizationType : {}", organizationType);
        if (organizationType.getId() == null) {
            return createOrganizationType(organizationType);
        }
        OrganizationType result = organizationTypeRepository.save(organizationType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("organizationType", organizationType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organizationTypes -> get all the organizationTypes.
     */
    @RequestMapping(value = "/organizationTypes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<OrganizationType> getAllOrganizationTypes() {
        log.debug("REST request to get all OrganizationTypes");
        return organizationTypeRepository.findAll();
            }

    /**
     * GET  /organizationTypes/:id -> get the "id" organizationType.
     */
    @RequestMapping(value = "/organizationTypes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrganizationType> getOrganizationType(@PathVariable Long id) {
        log.debug("REST request to get OrganizationType : {}", id);
        OrganizationType organizationType = organizationTypeRepository.findOne(id);
        return Optional.ofNullable(organizationType)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /organizationTypes/:id -> delete the "id" organizationType.
     */
    @RequestMapping(value = "/organizationTypes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOrganizationType(@PathVariable Long id) {
        log.debug("REST request to delete OrganizationType : {}", id);
        organizationTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("organizationType", id.toString())).build();
    }
}
