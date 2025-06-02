package com.greenacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @Column(name = "MASP", nullable = false, length = 10)
    private String masp;

    @Column(name = "TENSP", length = 100)
    private String tensp;

    @Column(name = "DVT", length = 50)
    private String dvt;

    @Column(name = "NUOCSX", length = 50)
    private String nuocsx;

    @Column(name = "GIA", length = 50)
    private String gia;

}