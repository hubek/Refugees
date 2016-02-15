package de.zalando.refugees.web.rest.mapper.implementation;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.repository.OrganizationTypeRepository;
import de.zalando.refugees.service.GeoCodingService;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.dto.OrganizationDTO;
import de.zalando.refugees.web.rest.mapper.DemandMapper;
import de.zalando.refugees.web.rest.mapper.OrganizationMapper;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.maps.model.LatLng;

/**
 * Mapper for the entity Organization and its DTO OrganizationDTO.
 */
public class DemandMapperImpl implements DemandMapper
{

	@Override
	public DemandDTO demandToDemandDTO( Demand demand )
	{
		if ( demand == null )
		{
			return null;
		}

		DemandDTO demandDTO = new DemandDTO();

		demandDTO.setBranchId( demandBranchId( demand ) );
		demandDTO.setSizeId( demandSizeId( demand ) );
		demandDTO.setGenderValue( demandGenderValue( demand ) );
		demandDTO.setDonationConditionId( demandDonationConditionId( demand ) );
		demandDTO.setSeasonValue( demandSeasonValue( demand ) );
		demandDTO.setDonationConditionValue( demandDonationConditionValue( demand ) );
		demandDTO.setSizeValue( demandSizeValue( demand ) );
		demandDTO.setSeasonId( demandSeasonId( demand ) );
		demandDTO.setGenderId( demandGenderId( demand ) );
		demandDTO.setCategoryName( demandCategoryName( demand ) );
		demandDTO.setCategoryId( demandCategoryId( demand ) );
		demandDTO.setId( demand.getId() );
		demandDTO.setQuantity( demand.getQuantity() );
		demandDTO.setDistance( demand.getDistance() );

		return demandDTO;
	}

	@Override
	public Demand demandDTOToDemand( DemandDTO demandDTO )
	{
		if ( demandDTO == null )
		{
			return null;
		}

		Demand demand = new Demand();

		demand.setDonationCondition( donationConditionFromId( demandDTO.getDonationConditionId() ) );
		demand.setGender( genderFromId( demandDTO.getGenderId() ) );
		demand.setSize( sizeFromId( demandDTO.getSizeId() ) );
		demand.setSeason( seasonFromId( demandDTO.getSeasonId() ) );
		demand.setCategory( categoryFromId( demandDTO.getCategoryId() ) );
		demand.setBranch( branchFromId( demandDTO.getBranchId() ) );
		demand.setId( demandDTO.getId() );
		demand.setQuantity( demandDTO.getQuantity() );
		demand.setDistance( demand.getDistance() );

		return demand;
	}

	private Long demandBranchId( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Branch branch = demand.getBranch();
		if ( branch == null )
		{
			return null;
		}
		Long id = branch.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private Long demandSizeId( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Size size = demand.getSize();
		if ( size == null )
		{
			return null;
		}
		Long id = size.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private String demandGenderValue( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Gender gender = demand.getGender();
		if ( gender == null )
		{
			return null;
		}
		String value = gender.getValue();
		if ( value == null )
		{
			return null;
		}
		return value;
	}

	private Long demandDonationConditionId( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		DonationCondition donationCondition = demand.getDonationCondition();
		if ( donationCondition == null )
		{
			return null;
		}
		Long id = donationCondition.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private String demandSeasonValue( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Season season = demand.getSeason();
		if ( season == null )
		{
			return null;
		}
		String value = season.getValue();
		if ( value == null )
		{
			return null;
		}
		return value;
	}

	private String demandDonationConditionValue( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		DonationCondition donationCondition = demand.getDonationCondition();
		if ( donationCondition == null )
		{
			return null;
		}
		String value = donationCondition.getValue();
		if ( value == null )
		{
			return null;
		}
		return value;
	}

	private String demandSizeValue( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Size size = demand.getSize();
		if ( size == null )
		{
			return null;
		}
		String value = size.getValue();
		if ( value == null )
		{
			return null;
		}
		return value;
	}

	private Long demandSeasonId( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Season season = demand.getSeason();
		if ( season == null )
		{
			return null;
		}
		Long id = season.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private Long demandGenderId( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Gender gender = demand.getGender();
		if ( gender == null )
		{
			return null;
		}
		Long id = gender.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private String demandCategoryName( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Category category = demand.getCategory();
		if ( category == null )
		{
			return null;
		}
		String name = category.getName();
		if ( name == null )
		{
			return null;
		}
		return name;
	}

	private Long demandCategoryId( Demand demand )
	{

		if ( demand == null )
		{
			return null;
		}
		Category category = demand.getCategory();
		if ( category == null )
		{
			return null;
		}
		Long id = category.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

}
