package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Size.
 */
@Entity
@Table(name = "size")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Size implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private String value;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Size size = (Size) o;
        if(size.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, size.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Size{" +
            "id=" + id +
            ", value='" + value + "'" +
            '}';
    }
}
