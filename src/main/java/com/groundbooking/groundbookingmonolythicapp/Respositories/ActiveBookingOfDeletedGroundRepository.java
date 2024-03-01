package com.groundbooking.groundbookingmonolythicapp.Respositories;

import com.groundbooking.groundbookingmonolythicapp.Entities.ActiveBookingsOfDeletedGround;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActiveBookingOfDeletedGroundRepository extends JpaRepository<ActiveBookingsOfDeletedGround, UUID> {
}
