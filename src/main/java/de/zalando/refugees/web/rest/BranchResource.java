package de.zalando.refugees.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.zalando.refugees.domain.Branch;
import de.zalando.refugees.service.BranchService;
import de.zalando.refugees.web.rest.util.HeaderUtil;
import de.zalando.refugees.web.rest.dto.BranchDTO;
import de.zalando.refugees.web.rest.mapper.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing Branch.
 */
@RestController
@RequestMapping("/api")
public class BranchResource {

    private final Logger log = LoggerFactory.getLogger(BranchResource.class);
        
    @Inject
    private BranchService branchService;
    
    @Inject
    private BranchMapper branchMapper;
    
    /**
     * POST  /branchs -> Create a new branch.
     */
    @RequestMapping(value = "/branchs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws URISyntaxException {
        log.debug("REST request to save Branch : {}", branchDTO);
        if (branchDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("branch", "idexists", "A new branch cannot already have an ID")).body(null);
        }
        BranchDTO result = branchService.save(branchDTO);
        return ResponseEntity.created(new URI("/api/branchs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("branch", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /branchs -> Updates an existing branch.
     */
    @RequestMapping(value = "/branchs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BranchDTO> updateBranch(@RequestBody BranchDTO branchDTO) throws URISyntaxException {
        log.debug("REST request to update Branch : {}", branchDTO);
        if (branchDTO.getId() == null) {
            return createBranch(branchDTO);
        }
        BranchDTO result = branchService.save(branchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("branch", branchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /branchs -> get all the branchs.
     */
    @RequestMapping(value = "/branchs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<BranchDTO> getAllBranchs() {
        log.debug("REST request to get all Branchs");
        return branchService.findAll();
            }

    /**
     * GET  /branchs/:id -> get the "id" branch.
     */
    @RequestMapping(value = "/branchs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BranchDTO> getBranch(@PathVariable Long id) {
        log.debug("REST request to get Branch : {}", id);
        BranchDTO branchDTO = branchService.findOne(id);
        return Optional.ofNullable(branchDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /branchs/:id -> delete the "id" branch.
     */
    @RequestMapping(value = "/branchs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        log.debug("REST request to delete Branch : {}", id);
        branchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("branch", id.toString())).build();
    }
}
