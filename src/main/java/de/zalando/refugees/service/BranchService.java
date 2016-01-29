package de.zalando.refugees.service;

import de.zalando.refugees.domain.Branch;
import de.zalando.refugees.repository.BranchRepository;
import de.zalando.refugees.web.rest.dto.BranchDTO;
import de.zalando.refugees.web.rest.mapper.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Branch.
 */
@Service
@Transactional
public class BranchService {

    private final Logger log = LoggerFactory.getLogger(BranchService.class);
    
    @Inject
    private BranchRepository branchRepository;
    
    @Inject
    private BranchMapper branchMapper;
    
    /**
     * Save a branch.
     * @return the persisted entity
     */
    public BranchDTO save(BranchDTO branchDTO) {
        log.debug("Request to save Branch : {}", branchDTO);
        Branch branch = branchMapper.branchDTOToBranch(branchDTO);
        branch = branchRepository.save(branch);
        BranchDTO result = branchMapper.branchToBranchDTO(branch);
        return result;
    }

    /**
     *  get all the branchs.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<BranchDTO> findAll() {
        log.debug("Request to get all Branchs");
        List<BranchDTO> result = branchRepository.findAll().stream()
            .map(branchMapper::branchToBranchDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one branch by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public BranchDTO findOne(Long id) {
        log.debug("Request to get Branch : {}", id);
        Branch branch = branchRepository.findOne(id);
        BranchDTO branchDTO = branchMapper.branchToBranchDTO(branch);
        return branchDTO;
    }

    /**
     *  delete the  branch by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
        branchRepository.delete(id);
    }
}
