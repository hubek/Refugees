package de.zalando.refugees.service;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.domain.DemandSpecification;
import de.zalando.refugees.domain.Offer;
import de.zalando.refugees.domain.OfferSpecification;
import de.zalando.refugees.repository.OfferRepository;
import de.zalando.refugees.web.rest.dto.OfferDTO;
import de.zalando.refugees.web.rest.mapper.OfferMapper;
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
 * Service Implementation for managing Offer.
 */
@Service
@Transactional
public class OfferService {

    private final Logger log = LoggerFactory.getLogger(OfferService.class);
    
    @Inject
    private OfferRepository offerRepository;
    
    @Inject
    private OfferMapper offerMapper;
    
    /**
     * Save a offer.
     * @return the persisted entity
     */
    public OfferDTO save(OfferDTO offerDTO) {
        log.debug("Request to save Offer : {}", offerDTO);
        Offer offer = offerMapper.offerDTOToOffer(offerDTO);
        offer = offerRepository.save(offer);
        OfferDTO result = offerMapper.offerToOfferDTO(offer);
        return result;
    }

    /**
     *  get all the offers.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Offer> findAll(Pageable pageable) {
        log.debug("Request to get all Offers");
        Page<Offer> result = offerRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one offer by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public OfferDTO findOne(Long id) {
        log.debug("Request to get Offer : {}", id);
        Offer offer = offerRepository.findOne(id);
        OfferDTO offerDTO = offerMapper.offerToOfferDTO(offer);
        return offerDTO;
    }

    /**
     *  delete the  offer by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Offer : {}", id);
        offerRepository.delete(id);
    }
    
	/**
	 * Get All Offers By Filter
	 * 
	 * @param pageable
	 * @param offer
	 * @return
	 */
	public Page< Offer > findAllByFilter( Pageable pageable, Offer offer )
	{
		OfferSpecification sp = new OfferSpecification( offer );

		return offerRepository.findAll( sp, pageable );
	}
}
