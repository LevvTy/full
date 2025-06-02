package com.greenacademy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @Column(name = "SOHD", nullable = false, length = 10)
    private String sohd;

    @Column(name = "NGHD")
    private LocalDate nghd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKH")
    private com.greenacademy.entity.KhachHang makh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANV")
    private com.greenacademy.entity.NhanVien manv;

    @Column(name = "TRIGIA", length = 50)
    private String trigia;

    @Column(name = "STTUS")
    private Boolean sttus;

}