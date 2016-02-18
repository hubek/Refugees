package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.maps.model.LatLng;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Branch.
 */
@Entity
@Table( name = "branch" )
@Cache( usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE )
public class Branch implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Column( name = "address" )
	private String address;

	@Column( name = "phone" )
	private String phone;

	@Column( name = "email" )
	private String email;

	@Column( name = "lng" )
	private Double lng;

	@Column( name = "lat" )
	private Double lat;

	@ManyToOne
	@JoinColumn( name = "organization_id" )
	private Organization organization;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress( String address )
	{
		this.address = address;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public Double getLng()
	{
		return lng;
	}

	public void setLng( Double lng )
	{
		this.lng = lng;
	}

	public Double getLat()
	{
		return lat;
	}

	public void setLat( Double lat )
	{
		this.lat = lat;
	}

	public Organization getOrganization()
	{
		return organization;
	}

	public void setOrganization( Organization organization )
	{
		this.organization = organization;
	}

	public LatLng getCoords()
	{
		return new LatLng( this.lat, this.lng );
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
		Branch branch = (Branch) o;
		if ( branch.id == null || id == null )
		{
			return false;
		}
		return Objects.equals( id, branch.id );
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	@Override
	public String toString()
	{
		return "Branch{" + "id=" + id + ", address='" + address + "'" + ", phone='" + phone + "'" + ", email='" + email
				+ "'" + ", lng='" + lng + "'" + ", lat='" + lat + "'" + '}';
	}
}
