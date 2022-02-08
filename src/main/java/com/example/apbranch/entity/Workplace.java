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
public class Workplace {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @Column(name = "map_id", updatable = true)
    @EqualsAndHashCode.Exclude
    private Map mapId;

    @Column(name = "workplace_number", updatable = true)
    @EqualsAndHashCode.Exclude
    private Integer workplaceNumber;

    @Column(name = "type", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean type;

    @Column(name = "next_to_window", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean nextToWindow;

    @Column(name = "hasPc", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean hasPc;

    @Column(name = "hasMonitor", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean hasMonitor;

    @Column(name = "has_keyboard", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean hasKeyboard;

    @Column(name = "has_mouse", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean hasMouse;

    @Column(name = "has_headset", updatable = true)
    @EqualsAndHashCode.Exclude
    private Boolean hasHeadset;
}
