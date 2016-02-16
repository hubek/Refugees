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

public class OfferSpecification implements Specification< Offer >
{

	private final Offer offer;

	public OfferSpecification( Offer offer )
	{
		this.offer = offer;
	}

	@Override
	public Predicate toPredicate( Root< Offer > root, CriteriaQuery< ? > query, CriteriaBuilder cb )
	{
		List< Predicate > predicates = new ArrayList< >();
		if ( offer.getCategory() != null )
		{
			predicates.add( cb.equal( root.get( "category" ), offer.getCategory() ) );
		}

		if ( offer.getOrganization() != null )
		{
			predicates.add( cb.equal( root.get( "organization" ), offer.getOrganization() ) );
		}
	
		if ( offer.getDonationCondition() != null )
		{
			predicates.add( cb.equal( root.get( "donationCondition" ), offer.getDonationCondition() ) );
		}
		
		if ( offer.getGender() != null )
		{
			predicates.add( cb.equal( root.get( "gender" ), offer.getGender() ) );
		}

		if ( offer.getSeason() != null )
		{
			predicates.add( cb.equal( root.get( "season" ), offer.getSeason() ) );
		}
		
		if( offer.getSize() != null )
		{
			predicates.add( cb.equal( root.get( "size" ), offer.getSize() ) );
		}

		return cb.and( predicates.toArray( new Predicate[0] ) );
	}

}
