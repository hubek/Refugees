package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.Donation_condtition;
import de.zalando.refugees.repository.Donation_condtitionRepository;
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
 * REST controller for managing Donation_condtition.
 */
@RestController
@RequestMapping("/api")
public class Donation_condtitionResource {

    private final Logger log = LoggerFactory.getLogger(Donation_condtitionResource.class);
        
    @Inject
    private Donation_condtitionRepository donation_condtitionRepository;
    
    /**
     * POST  /donation_condtitions -> Create a new donation_condtition.
     */
    @RequestMapping(value = "/donation_condtitions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Donation_condtition> createDonation_condtition(@RequestBody Donation_condtition donation_condtition) throws URISyntaxException {
        log.debug("REST request to save Donation_condtition : {}", donation_condtition);
        if (donation_condtition.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("donation_condtition", "idexists", "A new donation_condtition cannot already have an ID")).body(null);
        }
        Donation_condtition result = donation_condtitionRepository.save(donation_condtition);
        return ResponseEntity.created(new URI("/api/donation_condtitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("donation_condtition", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /donation_condtitions -> Updates an existing donation_condtition.
     */
    @RequestMapping(value = "/donation_condtitions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Donation_condtition> updateDonation_condtition(@RequestBody Donation_condtition donation_condtition) throws URISyntaxException {
        log.debug("REST request to update Donation_condtition : {}", donation_condtition);
        if (donation_condtition.getId() == null) {
            return createDonation_condtition(donation_condtition);
        }
        Donation_condtition result = donation_condtitionRepository.save(donation_condtition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("donation_condtition", donation_condtition.getId().toString()))
            .body(result);
    }

    /**
     * GET  /donation_condtitions -> get all the donation_condtitions.
     */
    @RequestMapping(value = "/donation_condtitions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Donation_condtition> getAllDonation_condtitions() {
        log.debug("REST request to get all Donation_condtitions");
        return donation_condtitionRepository.findAll();
            }

    /**
     * GET  /donation_condtitions/:id -> get the "id" donation_condtition.
     */
    @RequestMapping(value = "/donation_condtitions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Donation_condtition> getDonation_condtition(@PathVariable Long id) {
        log.debug("REST request to get Donation_condtition : {}", id);
        Donation_condtition donation_condtition = donation_condtitionRepository.findOne(id);
        return Optional.ofNullable(donation_condtition)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /donation_condtitions/:id -> delete the "id" donation_condtition.
     */
    @RequestMapping(value = "/donation_condtitions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDonation_condtition(@PathVariable Long id) {
        log.debug("REST request to delete Donation_condtition : {}", id);
        donation_condtitionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("donation_condtition", id.toString())).build();
    }
}
