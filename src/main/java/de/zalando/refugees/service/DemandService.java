package de.zalando.refugees.service;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.domain.DemandSpecification;
import de.zalando.refugees.domain.util.DemandComparator;
import de.zalando.refugees.repository.DemandRepository;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.mapper.DemandMapper;

import org.aspectj.weaver.patterns.ReferencePointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.model.LatLng;

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
public class DemandService
{
	@Autowired
	GeoCodingService geoCodingService;

	private final Logger log = LoggerFactory.getLogger( DemandService.class );

	@Inject
	private DemandRepository demandRepository;

	@Inject
	private DemandMapper demandMapper;

	/**
	 * Save a demand.
	 * 
	 * @return the persisted entity
	 */
	public DemandDTO save( DemandDTO demandDTO )
	{
		log.debug( "Request to save Demand : {}", demandDTO );
		Demand demand = demandMapper.demandDTOToDemand( demandDTO );
		demand = demandRepository.save( demand );
		DemandDTO result = demandMapper.demandToDemandDTO( demand );
		return result;
	}

	/**
	 * get all the demands.
	 * 
	 * @return the list of entities
	 */
	@Transactional( readOnly = true )
	public Page< Demand > findAll( Pageable pageable )
	{
		log.debug( "Request to get all Demands" );
		Page< Demand > result = demandRepository.findAll( pageable );
		return result;
	}

	/**
	 * get one demand by id.
	 * 
	 * @return the entity
	 */
	@Transactional( readOnly = true )
	public DemandDTO findOne( Long id )
	{
		log.debug( "Request to get Demand : {}", id );
		Demand demand = demandRepository.findOne( id );
		DemandDTO demandDTO = demandMapper.demandToDemandDTO( demand );
		return demandDTO;
	}

	/**
	 * delete the demand by id.
	 */
	public void delete( Long id )
	{
		log.debug( "Request to delete Demand : {}", id );
		demandRepository.delete( id );
	}

	/**
	 * Get All Demands By Filter
	 * @param pageable
	 * @param demand
	 * @return 
	 */
	public Page< Demand > findAllByFilter( Pageable pageable, Demand demand )
	{
		DemandSpecification sp = new DemandSpecification( demand );

		return demandRepository.findAll( sp, pageable );
	}
	
	/**
	 * Sort the returned demands by the distance from the reference point
	 * @param demands : List of demands
	 * @param referencePoint : LatLng instance represent the coordinations of the reference point
	 * @return Sorted list of demands by distance
	 */
	public List<Demand > sortByDistance( List< Demand > demands, LatLng referencePoint)
	{
		DemandComparator demandComparator = new DemandComparator( geoCodingService, referencePoint );
		demands.sort( demandComparator );
		
		return demands;
	}
}
