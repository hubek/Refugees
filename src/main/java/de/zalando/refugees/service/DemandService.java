package de.zalando.refugees.service;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.repository.DemandRepository;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.mapper.DemandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Demand.
 */
@Service
@Transactional
public class DemandService {

    private final Logger log = LoggerFactory.getLogger(DemandService.class);
    
    @Inject
    private DemandRepository demandRepository;
    
    @Inject
    private DemandMapper demandMapper;
    
    /**
     * Save a demand.
     * @return the persisted entity
     */
    public DemandDTO save(DemandDTO demandDTO) {
        log.debug("Request to save Demand : {}", demandDTO);
        Demand demand = demandMapper.demandDTOToDemand(demandDTO);
        demand = demandRepository.save(demand);
        DemandDTO result = demandMapper.demandToDemandDTO(demand);
        return result;
    }

    /**
     *  get all the demands.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Demand> findAll(Pageable pageable) {
        log.debug("Request to get all Demands");
        Page<Demand> result = demandRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one demand by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DemandDTO findOne(Long id) {
        log.debug("Request to get Demand : {}", id);
        Demand demand = demandRepository.findOne(id);
        DemandDTO demandDTO = demandMapper.demandToDemandDTO(demand);
        return demandDTO;
    }

    /**
     *  delete the  demand by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Demand : {}", id);
        demandRepository.delete(id);
    }
}
