package com.greenacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CthdId implements java.io.Serializable {
    private static final long serialVersionUID = 8225786213491306428L;
    @Column(name = "SOHD", nullable = false, length = 50)
    private String sohd;

    @Column(name = "MASP", nullable = false, length = 10)
    private String masp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CthdId entity = (CthdId) o;
        return Objects.equals(this.masp, entity.masp) &&
                Objects.equals(this.sohd, entity.sohd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masp, sohd);
    }

}