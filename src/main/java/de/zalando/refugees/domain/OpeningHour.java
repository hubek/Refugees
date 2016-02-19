package de.zalando.refugees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OpeningHour.
 */
@Entity
@Table(name = "opening_hour")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OpeningHour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "day")
    private String day;
    
    @Column(name = "open")
    private Integer open;
    
    @Column(name = "close")
    private Integer close;
    
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }
    
    public void setDay(String day) {
        this.day = day;
    }

    public Integer getOpen() {
        return open;
    }
    
    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getClose() {
        return close;
    }
    
    public void setClose(Integer close) {
        this.close = close;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpeningHour openingHour = (OpeningHour) o;
        if(openingHour.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, openingHour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OpeningHour{" +
            "id=" + id +
            ", day='" + day + "'" +
            ", open='" + open + "'" +
            ", close='" + close + "'" +
            '}';
    }
}
