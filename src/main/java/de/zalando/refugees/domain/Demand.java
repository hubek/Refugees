package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Demand.
 */
@Entity
@Table( name = "demand" )
@Cache( usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE )
public class Demand implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Column( name = "quantity" )
	private Integer quantity;

	@Column( name = "created" )
	private ZonedDateTime created;

	@ManyToOne
	@JoinColumn( name = "branch_id" )
	private Branch branch;

	@ManyToOne
	@JoinColumn( name = "category_id" )
	private Category category;

	@ManyToOne
	@JoinColumn( name = "season_id" )
	private Season season;

	@ManyToOne
	@JoinColumn( name = "gender_id" )
	private Gender gender;

	@ManyToOne
	@JoinColumn( name = "donation_condition_id" )
	private DonationCondition donationCondition;

	@ManyToOne
	@JoinColumn( name = "size_id" )
	private Size size;

	@ManyToOne
	@JoinColumn( name = "status_id" )
	private Status status;

	@Transient
	private Double distance;

	public Double getDistance()
	{
		return distance;
	}

	public void setDistance( Double distance )
	{
		this.distance = distance;
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity( Integer quantity )
	{
		if ( quantity == null )
		{
			quantity = 0;
		}

		this.quantity = quantity;
	}

	public Branch getBranch()
	{
		return branch;
	}

	public void setBranch( Branch branch )
	{
		this.branch = branch;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory( Category category )
	{
		this.category = category;
	}

	public Season getSeason()
	{
		return season;
	}

	public void setSeason( Season season )
	{
		this.season = season;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender( Gender gender )
	{
		this.gender = gender;
	}

	public DonationCondition getDonationCondition()
	{
		return donationCondition;
	}

	public void setDonationCondition( DonationCondition donationCondition )
	{
		this.donationCondition = donationCondition;
	}

	public Size getSize()
	{
		return size;
	}

	public void setSize( Size size )
	{
		this.size = size;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus( Status status )
	{
		this.status = status;
	}

	public ZonedDateTime getCreated()
	{
		return created;
	}

	public void setCreated( ZonedDateTime created )
	{
		if( created == null )
		{
			created = ZonedDateTime.now();
		}
		
		this.created = created;
	}

	@Override
	public boolean equals( Object o )
	{
		if ( this == o )
		{
			return true;
		}
		if ( o == null || getClass() != o.getClass() )
		{
			return false;
		}
		Demand demand = (Demand) o;
		if ( demand.id == null || id == null )
		{
			return false;
		}
		return Objects.equals( id, demand.id );
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	@Override
	public String toString()
	{
		return "Demand{" + "id=" + id + ", quantity='" + quantity + "'" + '}';
	}
}
