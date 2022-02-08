package com.example.apbranch.booking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
    private UUID workplaceId;
    private UUID userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isRecurring;
    private Integer frequency;
}
