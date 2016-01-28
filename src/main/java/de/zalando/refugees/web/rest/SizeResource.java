package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.Size;
import de.zalando.refugees.repository.SizeRepository;
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
 * REST controller for managing Size.
 */
@RestController
@RequestMapping("/api")
public class SizeResource {

    private final Logger log = LoggerFactory.getLogger(SizeResource.class);
        
    @Inject
    private SizeRepository sizeRepository;
    
    /**
     * POST  /sizes -> Create a new size.
     */
    @RequestMapping(value = "/sizes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Size> createSize(@RequestBody Size size) throws URISyntaxException {
        log.debug("REST request to save Size : {}", size);
        if (size.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("size", "idexists", "A new size cannot already have an ID")).body(null);
        }
        Size result = sizeRepository.save(size);
        return ResponseEntity.created(new URI("/api/sizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("size", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sizes -> Updates an existing size.
     */
    @RequestMapping(value = "/sizes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Size> updateSize(@RequestBody Size size) throws URISyntaxException {
        log.debug("REST request to update Size : {}", size);
        if (size.getId() == null) {
            return createSize(size);
        }
        Size result = sizeRepository.save(size);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("size", size.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sizes -> get all the sizes.
     */
    @RequestMapping(value = "/sizes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Size> getAllSizes() {
        log.debug("REST request to get all Sizes");
        return sizeRepository.findAll();
            }

    /**
     * GET  /sizes/:id -> get the "id" size.
     */
    @RequestMapping(value = "/sizes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Size> getSize(@PathVariable Long id) {
        log.debug("REST request to get Size : {}", id);
        Size size = sizeRepository.findOne(id);
        return Optional.ofNullable(size)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sizes/:id -> delete the "id" size.
     */
    @RequestMapping(value = "/sizes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSize(@PathVariable Long id) {
        log.debug("REST request to delete Size : {}", id);
        sizeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("size", id.toString())).build();
    }
}
