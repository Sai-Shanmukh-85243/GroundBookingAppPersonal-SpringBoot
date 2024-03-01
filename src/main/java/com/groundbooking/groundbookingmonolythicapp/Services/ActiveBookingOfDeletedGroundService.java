package com.groundbooking.groundbookingmonolythicapp.Services;

import com.groundbooking.groundbookingmonolythicapp.Entities.ActiveBookingsOfDeletedGround;
import com.groundbooking.groundbookingmonolythicapp.Entities.GroundDetails;

import java.util.Optional;

public interface ActiveBookingOfDeletedGroundService {
    public void addActiveBookingOfDeletedGround(GroundDetails groundDetails);
}
