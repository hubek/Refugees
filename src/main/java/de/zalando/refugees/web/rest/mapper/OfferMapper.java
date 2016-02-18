package de.zalando.refugees.web.rest.mapper;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.web.rest.dto.OfferDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Offer and its DTO OfferDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OfferMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "season.id", target = "seasonId")
    @Mapping(source = "size.id", target = "sizeId")
    @Mapping(source = "donationCondition.id", target = "donationConditionId")
    @Mapping(source = "gender.id", target = "genderId")
    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "status.id", target = "statusId")
    OfferDTO offerToOfferDTO(Offer offer);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "seasonId", target = "season")
    @Mapping(source = "sizeId", target = "size")
    @Mapping(source = "donationConditionId", target = "donationCondition")
    @Mapping(source = "genderId", target = "gender")
    @Mapping(source = "organizationId", target = "organization")
    @Mapping(source = "statusId", target = "status")
    Offer offerDTOToOffer(OfferDTO offerDTO);

    default Category categoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }

    default Season seasonFromId(Long id) {
        if (id == null) {
            return null;
        }
        Season season = new Season();
        season.setId(id);
        return season;
    }

    default Size sizeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Size size = new Size();
        size.setId(id);
        return size;
    }

    default DonationCondition donationConditionFromId(Long id) {
        if (id == null) {
            return null;
        }
        DonationCondition donationCondition = new DonationCondition();
        donationCondition.setId(id);
        return donationCondition;
    }

    default Gender genderFromId(Long id) {
        if (id == null) {
            return null;
        }
        Gender gender = new Gender();
        gender.setId(id);
        return gender;
    }

    default Organization organizationFromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }

    default Status statusFromId(Long id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setId(id);
        return status;
    }
}
