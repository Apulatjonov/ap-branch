package com.example.apbranch.entity;

import com.example.apbranch.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vacation {

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
    @Column(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "vacation_start")
    @EqualsAndHashCode.Exclude
    private LocalDate vacationStart;

    @Column(name = "vacation_end")
    @EqualsAndHashCode.Exclude
    private LocalDate vacationEnd;
}