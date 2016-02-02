package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.service.DemandService;
import de.zalando.refugees.web.rest.util.HeaderUtil;
import de.zalando.refugees.web.rest.util.PaginationUtil;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.mapper.DemandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Demand.
 */
@RestController
@RequestMapping("/api")
public class DemandResource {

    private final Logger log = LoggerFactory.getLogger(DemandResource.class);
        
    @Inject
    private DemandService demandService;
    
    @Inject
    private DemandMapper demandMapper;
    
    /**
     * POST  /demands -> Create a new demand.
     */
    @RequestMapping(value = "/demands",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DemandDTO> createDemand(@RequestBody DemandDTO demandDTO) throws URISyntaxException {
        log.debug("REST request to save Demand : {}", demandDTO);
        if (demandDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("demand", "idexists", "A new demand cannot already have an ID")).body(null);
        }
        DemandDTO result = demandService.save(demandDTO);
        return ResponseEntity.created(new URI("/api/demands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("demand", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demands -> Updates an existing demand.
     */
    @RequestMapping(value = "/demands",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DemandDTO> updateDemand(@RequestBody DemandDTO demandDTO) throws URISyntaxException {
        log.debug("REST request to update Demand : {}", demandDTO);
        if (demandDTO.getId() == null) {
            return createDemand(demandDTO);
        }
        DemandDTO result = demandService.save(demandDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("demand", demandDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demands -> get all the demands.
     */
    @RequestMapping(value = "/demands",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<DemandDTO>> getAllDemands(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Demands");
        Page<Demand> page = demandService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/demands");
        return new ResponseEntity<>(page.getContent().stream()
            .map(demandMapper::demandToDemandDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /demands/:id -> get the "id" demand.
     */
    @RequestMapping(value = "/demands/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DemandDTO> getDemand(@PathVariable Long id) {
        log.debug("REST request to get Demand : {}", id);
        DemandDTO demandDTO = demandService.findOne(id);
        return Optional.ofNullable(demandDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /demands/:id -> delete the "id" demand.
     */
    @RequestMapping(value = "/demands/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDemand(@PathVariable Long id) {
        log.debug("REST request to delete Demand : {}", id);
        demandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("demand", id.toString())).build();
    }
}
