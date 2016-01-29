package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.Gender;
import de.zalando.refugees.repository.GenderRepository;
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
 * REST controller for managing Gender.
 */
@RestController
@RequestMapping("/api")
public class GenderResource {

    private final Logger log = LoggerFactory.getLogger(GenderResource.class);
        
    @Inject
    private GenderRepository genderRepository;
    
    /**
     * POST  /genders -> Create a new gender.
     */
    @RequestMapping(value = "/genders",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Gender> createGender(@RequestBody Gender gender) throws URISyntaxException {
        log.debug("REST request to save Gender : {}", gender);
        if (gender.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("gender", "idexists", "A new gender cannot already have an ID")).body(null);
        }
        Gender result = genderRepository.save(gender);
        return ResponseEntity.created(new URI("/api/genders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("gender", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /genders -> Updates an existing gender.
     */
    @RequestMapping(value = "/genders",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Gender> updateGender(@RequestBody Gender gender) throws URISyntaxException {
        log.debug("REST request to update Gender : {}", gender);
        if (gender.getId() == null) {
            return createGender(gender);
        }
        Gender result = genderRepository.save(gender);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("gender", gender.getId().toString()))
            .body(result);
    }

    /**
     * GET  /genders -> get all the genders.
     */
    @RequestMapping(value = "/genders",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Gender> getAllGenders() {
        log.debug("REST request to get all Genders");
        return genderRepository.findAll();
            }

    /**
     * GET  /genders/:id -> get the "id" gender.
     */
    @RequestMapping(value = "/genders/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Gender> getGender(@PathVariable Long id) {
        log.debug("REST request to get Gender : {}", id);
        Gender gender = genderRepository.findOne(id);
        return Optional.ofNullable(gender)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /genders/:id -> delete the "id" gender.
     */
    @RequestMapping(value = "/genders/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteGender(@PathVariable Long id) {
        log.debug("REST request to delete Gender : {}", id);
        genderRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("gender", id.toString())).build();
    }
}
