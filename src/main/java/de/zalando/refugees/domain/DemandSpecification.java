package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

/**
 * A Demand.
 */

public class DemandSpecification implements Specification< Demand >
{

	private final Demand demand;

	public DemandSpecification( Demand demand )
	{
		this.demand = demand;
	}

	@Override
	public Predicate toPredicate( Root< Demand > root, CriteriaQuery< ? > query, CriteriaBuilder cb )
	{
		List< Predicate > predicates = new ArrayList< >();
		if ( demand.getCategory() != null )
		{
			predicates.add( cb.equal( root.get( "category" ), demand.getCategory() ) );
		}

		if ( demand.getBranch() != null )
		{
			predicates.add( cb.equal( root.get( "branch" ), demand.getBranch() ) );
		}
	
		if ( demand.getDonationCondition() != null )
		{
			predicates.add( cb.equal( root.get( "donationCondition" ), demand.getDonationCondition() ) );
		}
		
		if ( demand.getGender() != null )
		{
			predicates.add( cb.equal( root.get( "gender" ), demand.getGender() ) );
		}

		if ( demand.getSeason() != null )
		{
			predicates.add( cb.equal( root.get( "season" ), demand.getSeason() ) );
		}
		
		if( demand.getSize() != null )
		{
			predicates.add( cb.equal( root.get( "size" ), demand.getSize() ) );
		}
		
		if ( demand.getStatus() != null )
		{
			predicates.add( cb.equal( root.get( "status" ), demand.getStatus() ) );
		}

		return cb.and( predicates.toArray( new Predicate[0] ) );
	}

}
