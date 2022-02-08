package com.example.apbranch.booking;

import com.example.apbranch.entity.Workplace;
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
public class Booking {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @Column(name = "workplace_id")
    @EqualsAndHashCode.Exclude
    private Workplace workplace;

    @ManyToOne
    @Column(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "start_date")
    @EqualsAndHashCode.Exclude
    private LocalDate startDate;

    @Column(name = "end_date")
    @EqualsAndHashCode.Exclude
    private LocalDate endDate;

    @Column(name = "is_recurring")
    @EqualsAndHashCode.Exclude
    private Boolean isRecurring;

    @Column(name = "frequency")
    @EqualsAndHashCode.Exclude
    private Integer frequency;
}
