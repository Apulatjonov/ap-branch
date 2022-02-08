package com.example.apbranch.user;

import com.example.apbranch.entity.Workplace;
import com.example.apbranch.user.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate,id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false)
    @EqualsAndHashCode.Exclude
    private UUID id;

    @Column(name = "telegram_chat_id", updatable = true)
    @EqualsAndHashCode.Exclude
    private String telegramChatId;

    @Column(name = "first_name", updatable = true)
    @EqualsAndHashCode.Exclude
    private String firstName;

    @Column(name = "last_name", updatable = true)
    @EqualsAndHashCode.Exclude
    private String lastName;

    @Column(name = "email", updatable = true)
    @EqualsAndHashCode.Exclude
    private String email;

    @Column(name = "role", updatable = true)
    @EqualsAndHashCode.Exclude
    private Roles role;

    @Column(name = "employment_start", updatable = true)
    @EqualsAndHashCode.Exclude
    private LocalDate employmentStart;

    @Column(name = "employment_end", updatable = true)
    @EqualsAndHashCode.Exclude
    private LocalDate employmentEnd;

    @Column(name = "workplace_id", updatable = true)
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Workplace workplace;
}
