package de.zalando.refugees.web.rest.mapper;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.web.rest.dto.DemandDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Demand and its DTO DemandDTO.
 */
@Mapper( componentModel = "spring", uses = {} )
public interface DemandMapper
{

	@Mapping( source = "branch.id", target = "branchId" )
	@Mapping( source = "category.id", target = "categoryId" )
	@Mapping( source = "category.name", target = "categoryName" )
	@Mapping( source = "season.id", target = "seasonId" )
	@Mapping( source = "season.value", target = "seasonValue" )
	@Mapping( source = "gender.id", target = "genderId" )
	@Mapping( source = "gender.value", target = "genderValue" )
	@Mapping( source = "donationCondition.id", target = "donationConditionId" )
	@Mapping( source = "donationCondition.value", target = "donationConditionValue" )
	@Mapping( source = "size.id", target = "sizeId" )
	@Mapping( source = "size.value", target = "sizeValue" )
	DemandDTO demandToDemandDTO( Demand demand );

	@Mapping( source = "branchId", target = "branch" )
	@Mapping( source = "categoryId", target = "category" )
	@Mapping( source = "seasonId", target = "season" )
	@Mapping( source = "genderId", target = "gender" )
	@Mapping( source = "donationConditionId", target = "donationCondition" )
	@Mapping( source = "sizeId", target = "size" )
	Demand demandDTOToDemand( DemandDTO demandDTO );

	default Branch branchFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		Branch branch = new Branch();
		branch.setId( id );
		return branch;
	}

	default Category categoryFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		Category category = new Category();
		category.setId( id );
		return category;
	}

	default Season seasonFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		Season season = new Season();
		season.setId( id );
		return season;
	}

	default Gender genderFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		Gender gender = new Gender();
		gender.setId( id );
		return gender;
	}

	default DonationCondition donationConditionFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		DonationCondition donationCondition = new DonationCondition();
		donationCondition.setId( id );
		return donationCondition;
	}

	default Size sizeFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		Size size = new Size();
		size.setId( id );
		return size;
	}

	default Status statusFromId( Long id )
	{
		if ( id == null )
		{
			return null;
		}
		Status status = new Status();
		status.setId( id );
		return status;
	}
}
