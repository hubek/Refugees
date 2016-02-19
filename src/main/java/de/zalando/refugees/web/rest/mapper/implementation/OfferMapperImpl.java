package de.zalando.refugees.web.rest.mapper.implementation;

import de.zalando.refugees.domain.Category;
import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.domain.DonationCondition;
import de.zalando.refugees.domain.Gender;
import de.zalando.refugees.domain.Offer;
import de.zalando.refugees.domain.Organization;
import de.zalando.refugees.domain.Season;
import de.zalando.refugees.domain.Size;
import de.zalando.refugees.domain.Status;
import de.zalando.refugees.web.rest.dto.OfferDTO;
import de.zalando.refugees.web.rest.mapper.OfferMapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(	value = "org.mapstruct.ap.MappingProcessor",
			comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)" )
//@Component
public class OfferMapperImpl implements OfferMapper
{

	@Override
	public OfferDTO offerToOfferDTO( Offer offer )
	{
		if ( offer == null )
		{
			return null;
		}

		OfferDTO offerDTO = new OfferDTO();

		offerDTO.setOrganizationId( offerOrganizationId( offer ) );
		offerDTO.setSizeId( offerSizeId( offer ) );
		offerDTO.setDonationConditionId( offerDonationConditionId( offer ) );
		offerDTO.setSeasonId( offerSeasonId( offer ) );
		offerDTO.setGenderId( offerGenderId( offer ) );
		offerDTO.setCategoryId( offerCategoryId( offer ) );
		offerDTO.setStatusId( offerStatusId( offer ) );
		offerDTO.setId( offer.getId() );

		offerDTO.setOrganizationName( offerOrganizationName( offer ) );
		offerDTO.setSizeValue( offerSizeValue( offer ) );
		offerDTO.setDonationConditionValue( offerDonationConditionValue( offer ) );
		offerDTO.setSeasonValue( offerSeasonValue( offer ) );
		offerDTO.setGenderValue( offerGenderValue( offer ) );
		offerDTO.setCategoryName( offerCategoryName( offer ) );
		offerDTO.setStatusValue( offerStatusValue( offer ) );

		offerDTO.setQuantity( offer.getQuantity() );

		return offerDTO;
	}

	@Override
	public Offer offerDTOToOffer( OfferDTO offerDTO )
	{
		if ( offerDTO == null )
		{
			return null;
		}

		Offer offer = new Offer();

		offer.setDonationCondition( donationConditionFromId( offerDTO.getDonationConditionId() ) );
		offer.setSize( sizeFromId( offerDTO.getSizeId() ) );
		offer.setGender( genderFromId( offerDTO.getGenderId() ) );
		offer.setOrganization( organizationFromId( offerDTO.getOrganizationId() ) );
		offer.setSeason( seasonFromId( offerDTO.getSeasonId() ) );
		offer.setCategory( categoryFromId( offerDTO.getCategoryId() ) );
		offer.setId( offerDTO.getId() );
		offer.setQuantity( offerDTO.getQuantity() );
		offer.setStatus( statusFromId( offerDTO.getStatusId() ) );

		return offer;
	}

	private Long offerOrganizationId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Organization organization = offer.getOrganization();
		if ( organization == null )
		{
			return null;
		}
		Long id = organization.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private Long offerSizeId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Size size = offer.getSize();
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

	private Long offerDonationConditionId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		DonationCondition donationCondition = offer.getDonationCondition();
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

	private Long offerSeasonId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Season season = offer.getSeason();
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

	private Long offerGenderId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Gender gender = offer.getGender();
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

	private Long offerCategoryId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Category category = offer.getCategory();
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

	private Long offerStatusId( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Status status = offer.getStatus();
		if ( status == null )
		{
			return null;
		}
		Long id = status.getId();
		if ( id == null )
		{
			return null;
		}
		return id;
	}

	private String offerSizeValue( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Size size = offer.getSize();
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

	private String offerOrganizationName( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Organization organization = offer.getOrganization();
		if ( organization == null )
		{
			return null;
		}
		String name = organization.getName();
		if ( name == null )
		{
			return null;
		}
		return name;
	}

	private String offerDonationConditionValue( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		DonationCondition donationCondition = offer.getDonationCondition();
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

	private String offerSeasonValue( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Season season = offer.getSeason();
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

	private String offerGenderValue( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Gender gender = offer.getGender();
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

	private String offerCategoryName( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Category category = offer.getCategory();
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

	private String offerStatusValue( Offer offer )
	{

		if ( offer == null )
		{
			return null;
		}
		Status status = offer.getStatus();
		if ( status == null )
		{
			return null;
		}
		String value = status.getValue();
		if ( value == null )
		{
			return null;
		}
		return value;
	}

}
