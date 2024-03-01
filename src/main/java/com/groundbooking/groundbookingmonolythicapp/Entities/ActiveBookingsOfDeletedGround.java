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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActiveBookingsOfDeletedGround {
    @Id
    @GeneratedValue
    private UUID ActiveBookingOfDeletedGroundId;
    private String BookedBy;
    private String EmailOfBookedBy;
    private String ContactOfBookedBy;
    private LocalDate BookedOn;
    private LocalDate BookedFor;
    private LocalTime BookingStartTime;
    private LocalTime BookingEndTime;
    private String GroundName;
    private String GroundLocation;
    private String GroundPrice;
    private String GroundOwner;
    //private String GroundAddedOn;
    private LocalDate GroundDeletedOn;
    private LocalTime GroundDeletedTime;
}
