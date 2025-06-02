package com.greenacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "MAKH", nullable = false, length = 10)
    private String makh;

    @Column(name = "HOTEN", nullable = false, length = 100)
    private String hoten;

    @Column(name = "DCHI")
    private String dchi;

    @Column(name = "SDT", length = 10)
    private String sdt;

    @Column(name = "NGSINH")
    private LocalDate ngsinh;

    @Column(name = "DOANHSO", precision = 15, scale = 2)
    private BigDecimal doanhso;

    @ColumnDefault("(now())")
    @Column(name = "NGDK")
    private LocalDate ngdk;

}