package de.zalando.refugees.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Branch entity.
 */
public class BranchDTO implements Serializable
{

	private Long id;

	private String address;

	private String phone;

	private String email;

	private Double lng;

	private Double lat;

	private Long organizationId;
	
	private String organizationName;

	public String getOrganizationName()
	{
		return organizationName;
	}

	public void setOrganizationName( String organizationName )
	{
		this.organizationName = organizationName;
	}

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

	public Long getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId( Long organizationId )
	{
		this.organizationId = organizationId;
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

		BranchDTO branchDTO = (BranchDTO) o;

		if ( !Objects.equals( id, branchDTO.id ) )
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	@Override
	public String toString()
	{
		return "BranchDTO{" + "id=" + id + ", address='" + address + "'" + ", phone='" + phone + "'" + ", email='"
				+ email + "'" + ", lng='" + lng + "'" + ", lat='" + lat + "'" + '}';
	}
}
