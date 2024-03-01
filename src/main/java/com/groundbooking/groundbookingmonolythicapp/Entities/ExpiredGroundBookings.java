package com.groundbooking.groundbookingmonolythicapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpiredGroundBookings {
    @GeneratedValue
    @Id
    private UUID ExpiredGroundBookingsId;
    private String BookedBy;
    private LocalDate BookedOn;
    private LocalDate BookedFor;
    private LocalTime BookingStartTime;
    private LocalTime BookingEndTime;
    private String GroundName;
    private String GroundLocation;
    private String GroundPrice;
    private String GroundOwner;

}
