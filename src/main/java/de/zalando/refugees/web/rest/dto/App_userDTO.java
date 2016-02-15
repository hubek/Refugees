package de.zalando.refugees.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the App_user entity.
 */
public class App_userDTO implements Serializable {

    private Long id;

    private Long branchId;
    private Long ji_userId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    public Long getJi_userId() {
        return ji_userId;
    }

    public void setJi_userId(Long userId) {
        this.ji_userId = userId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        App_userDTO app_userDTO = (App_userDTO) o;

        if ( ! Objects.equals(id, app_userDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "App_userDTO{" +
            "id=" + id +
            '}';
    }
}
