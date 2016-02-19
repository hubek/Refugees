package de.zalando.refugees.web.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Demand entity.
 */
public class DemandDTO implements Serializable
{

	private Long id;

	private Integer quantity;

	private Long branchId;

	private Long categoryId;

	private String categoryName;

	private Long seasonId;

	private String seasonValue;

	private Long genderId;

	private String genderValue;

	private Long donationConditionId;

	private String donationConditionValue;

	private Long sizeId;

	private String sizeValue;

	private Double distance;

	private Long statusId;

	private String statusValue;

	private ZonedDateTime created;

	public ZonedDateTime getCreated()
	{
		return created;
	}

	public void setCreated( ZonedDateTime created )
	{
		this.created = created;
	}

	public String getStatusValue()
	{
		return statusValue;
	}

	public void setStatusValue( String statusValue )
	{
		this.statusValue = statusValue;
	}

	public Long getStatusId()
	{
		return statusId;
	}

	public void setStatusId( Long statusId )
	{
		this.statusId = statusId;
	}

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
		this.quantity = quantity;
	}

	public Long getBranchId()
	{
		return branchId;
	}

	public void setBranchId( Long branchId )
	{
		this.branchId = branchId;
	}

	public Long getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId( Long categoryId )
	{
		this.categoryId = categoryId;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName( String categoryName )
	{
		this.categoryName = categoryName;
	}

	public Long getSeasonId()
	{
		return seasonId;
	}

	public void setSeasonId( Long seasonId )
	{
		this.seasonId = seasonId;
	}

	public String getSeasonValue()
	{
		return seasonValue;
	}

	public void setSeasonValue( String seasonValue )
	{
		this.seasonValue = seasonValue;
	}

	public Long getGenderId()
	{
		return genderId;
	}

	public void setGenderId( Long genderId )
	{
		this.genderId = genderId;
	}

	public String getGenderValue()
	{
		return genderValue;
	}

	public void setGenderValue( String genderValue )
	{
		this.genderValue = genderValue;
	}

	public Long getDonationConditionId()
	{
		return donationConditionId;
	}

	public void setDonationConditionId( Long donationConditionId )
	{
		this.donationConditionId = donationConditionId;
	}

	public String getDonationConditionValue()
	{
		return donationConditionValue;
	}

	public void setDonationConditionValue( String donationConditionValue )
	{
		this.donationConditionValue = donationConditionValue;
	}

	public Long getSizeId()
	{
		return sizeId;
	}

	public void setSizeId( Long sizeId )
	{
		this.sizeId = sizeId;
	}

	public String getSizeValue()
	{
		return sizeValue;
	}

	public void setSizeValue( String sizeValue )
	{
		this.sizeValue = sizeValue;
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

		DemandDTO demandDTO = (DemandDTO) o;

		if ( !Objects.equals( id, demandDTO.id ) )
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
		return "DemandDTO{" + "id=" + id + ", quantity='" + quantity + "'" + '}';
	}
}
