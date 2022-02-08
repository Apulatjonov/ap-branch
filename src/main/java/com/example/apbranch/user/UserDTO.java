package com.example.apbranch.user;

import com.example.apbranch.user.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String telegramChatId;
    private String firstName;
    private String lastName;
    private String email;
    private Roles role;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;
    private UUID workplaceId;
}
