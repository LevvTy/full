package com.greenacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;


    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "address", nullable = false, length = 250)
    private String address;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

}