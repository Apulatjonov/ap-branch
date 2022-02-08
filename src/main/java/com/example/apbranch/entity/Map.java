package com.example.apbranch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false)
    @EqualsAndHashCode.Exclude
    private UUID id;

    @ManyToOne
    @Column(name = "office_id", updatable = false)
    @EqualsAndHashCode.Exclude
    private Office officeId;

    @Column(name = "floor", updatable = false)
    @EqualsAndHashCode.Exclude
    private String floor;

    @Column(name = "has_kitchen", updatable = false)
    @EqualsAndHashCode.Exclude
    private boolean hasKitchen;

    @Column(name = "count_rooms", updatable = false)
    @EqualsAndHashCode.Exclude
    private String countRooms;
}
