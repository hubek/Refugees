package de.zalando.refugees.service;

import de.zalando.refugees.domain.Organization;
import de.zalando.refugees.repository.OrganizationRepository;
import de.zalando.refugees.web.rest.dto.OrganizationDTO;
import de.zalando.refugees.web.rest.mapper.OrganizationMapper;
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
 * Service Implementation for managing Organization.
 */
@Service
@Transactional
public class OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationService.class);
    
    @Inject
    private OrganizationRepository organizationRepository;
    
    @Inject
    private OrganizationMapper organizationMapper;
    
    /**
     * Save a organization.
     * @return the persisted entity
     */
    public OrganizationDTO save(OrganizationDTO organizationDTO) {
        log.debug("Request to save Organization : {}", organizationDTO);
        Organization organization = organizationMapper.organizationDTOToOrganization(organizationDTO);
        organization = organizationRepository.save(organization);
        OrganizationDTO result = organizationMapper.organizationToOrganizationDTO(organization);
        return result;
    }

    /**
     *  get all the organizations.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<OrganizationDTO> findAll() {
        log.debug("Request to get all Organizations");
        List<OrganizationDTO> result = organizationRepository.findAll().stream()
            .map(organizationMapper::organizationToOrganizationDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one organization by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public OrganizationDTO findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        Organization organization = organizationRepository.findOne(id);
        OrganizationDTO organizationDTO = organizationMapper.organizationToOrganizationDTO(organization);
        return organizationDTO;
    }

    /**
     *  delete the  organization by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        organizationRepository.delete(id);
    }
}
