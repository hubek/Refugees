package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.DonationCondition;
import de.zalando.refugees.repository.DonationConditionRepository;
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
 * REST controller for managing DonationCondition.
 */
@RestController
@RequestMapping("/api")
public class DonationConditionResource {

    private final Logger log = LoggerFactory.getLogger(DonationConditionResource.class);
        
    @Inject
    private DonationConditionRepository donationConditionRepository;
    
    /**
     * POST  /donationConditions -> Create a new donationCondition.
     */
    @RequestMapping(value = "/donationConditions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DonationCondition> createDonationCondition(@RequestBody DonationCondition donationCondition) throws URISyntaxException {
        log.debug("REST request to save DonationCondition : {}", donationCondition);
        if (donationCondition.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("donationCondition", "idexists", "A new donationCondition cannot already have an ID")).body(null);
        }
        DonationCondition result = donationConditionRepository.save(donationCondition);
        return ResponseEntity.created(new URI("/api/donationConditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("donationCondition", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /donationConditions -> Updates an existing donationCondition.
     */
    @RequestMapping(value = "/donationConditions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DonationCondition> updateDonationCondition(@RequestBody DonationCondition donationCondition) throws URISyntaxException {
        log.debug("REST request to update DonationCondition : {}", donationCondition);
        if (donationCondition.getId() == null) {
            return createDonationCondition(donationCondition);
        }
        DonationCondition result = donationConditionRepository.save(donationCondition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("donationCondition", donationCondition.getId().toString()))
            .body(result);
    }

    /**
     * GET  /donationConditions -> get all the donationConditions.
     */
    @RequestMapping(value = "/donationConditions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<DonationCondition> getAllDonationConditions() {
        log.debug("REST request to get all DonationConditions");
        return donationConditionRepository.findAll();
            }

    /**
     * GET  /donationConditions/:id -> get the "id" donationCondition.
     */
    @RequestMapping(value = "/donationConditions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DonationCondition> getDonationCondition(@PathVariable Long id) {
        log.debug("REST request to get DonationCondition : {}", id);
        DonationCondition donationCondition = donationConditionRepository.findOne(id);
        return Optional.ofNullable(donationCondition)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /donationConditions/:id -> delete the "id" donationCondition.
     */
    @RequestMapping(value = "/donationConditions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDonationCondition(@PathVariable Long id) {
        log.debug("REST request to delete DonationCondition : {}", id);
        donationConditionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("donationCondition", id.toString())).build();
    }
}
