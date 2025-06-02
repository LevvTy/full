package com.greenacademy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CTHD")
public class Cthd {
    @EmbeddedId
    private CthdId id;

    @MapsId("masp")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MASP", nullable = false)
    private com.greenacademy.entity.SanPham masp;

    @Column(name = "SL")
    private Integer sl;

}