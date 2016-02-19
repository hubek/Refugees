package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.OpeningHour;
import de.zalando.refugees.repository.OpeningHourRepository;
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
 * REST controller for managing OpeningHour.
 */
@RestController
@RequestMapping("/api")
public class OpeningHourResource {

    private final Logger log = LoggerFactory.getLogger(OpeningHourResource.class);
        
    @Inject
    private OpeningHourRepository openingHourRepository;
    
    /**
     * POST  /openingHours -> Create a new openingHour.
     */
    @RequestMapping(value = "/openingHours",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OpeningHour> createOpeningHour(@RequestBody OpeningHour openingHour) throws URISyntaxException {
        log.debug("REST request to save OpeningHour : {}", openingHour);
        if (openingHour.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("openingHour", "idexists", "A new openingHour cannot already have an ID")).body(null);
        }
        OpeningHour result = openingHourRepository.save(openingHour);
        return ResponseEntity.created(new URI("/api/openingHours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("openingHour", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /openingHours -> Updates an existing openingHour.
     */
    @RequestMapping(value = "/openingHours",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OpeningHour> updateOpeningHour(@RequestBody OpeningHour openingHour) throws URISyntaxException {
        log.debug("REST request to update OpeningHour : {}", openingHour);
        if (openingHour.getId() == null) {
            return createOpeningHour(openingHour);
        }
        OpeningHour result = openingHourRepository.save(openingHour);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("openingHour", openingHour.getId().toString()))
            .body(result);
    }

    /**
     * GET  /openingHours -> get all the openingHours.
     */
    @RequestMapping(value = "/openingHours",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<OpeningHour> getAllOpeningHours() {
        log.debug("REST request to get all OpeningHours");
        return openingHourRepository.findAll();
            }

    /**
     * GET  /openingHours/:id -> get the "id" openingHour.
     */
    @RequestMapping(value = "/openingHours/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OpeningHour> getOpeningHour(@PathVariable Long id) {
        log.debug("REST request to get OpeningHour : {}", id);
        OpeningHour openingHour = openingHourRepository.findOne(id);
        return Optional.ofNullable(openingHour)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /openingHours/:id -> delete the "id" openingHour.
     */
    @RequestMapping(value = "/openingHours/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOpeningHour(@PathVariable Long id) {
        log.debug("REST request to delete OpeningHour : {}", id);
        openingHourRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("openingHour", id.toString())).build();
    }
}
