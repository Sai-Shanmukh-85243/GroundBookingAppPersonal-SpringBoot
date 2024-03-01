package com.groundbooking.groundbookingmonolythicapp.Respositories;

import com.groundbooking.groundbookingmonolythicapp.Entities.ExpiredGroundBookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpiredGroundBookingsRepository extends JpaRepository<ExpiredGroundBookings, UUID> {

}
