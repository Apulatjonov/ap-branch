package com.example.apbranch.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Office {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false)
    @EqualsAndHashCode.Exclude
    private UUID id;

    @Column(name = "country")
    @EqualsAndHashCode.Exclude
    private String country;

    @Column(name = "city")
    @EqualsAndHashCode.Exclude
    private String city;

    @Column(name = "name")
    @EqualsAndHashCode.Exclude
    private String name;

    @Column(name = "address")
    @EqualsAndHashCode.Exclude
    private String address;

    @Column(name = "parking")
    @EqualsAndHashCode.Exclude
    private Integer parking;
}