package de.zalando.refugees.web.rest.mapper;

import de.zalando.refugees.domain.*;
import de.zalando.refugees.web.rest.dto.AppUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppUser and its DTO AppUserDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppUserMapper {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "user.id", target = "userId")
    AppUserDTO appUserToAppUserDTO(AppUser appUser);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "userId", target = "user")
    AppUser appUserDTOToAppUser(AppUserDTO appUserDTO);

    default Branch branchFromId(Long id) {
        if (id == null) {
            return null;
        }
        Branch branch = new Branch();
        branch.setId(id);
        return branch;
    }

    default User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
