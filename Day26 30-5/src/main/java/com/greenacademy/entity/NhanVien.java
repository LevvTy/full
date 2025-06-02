package com.greenacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @Column(name = "MANV", nullable = false, length = 10)
    private String manv;

    @Column(name = "HOTEN", length = 50)
    private String hoten;

    @Column(name = "NGVL")
    private LocalDate ngvl;

    @Column(name = "SODT", length = 10)
    private String sodt;

}