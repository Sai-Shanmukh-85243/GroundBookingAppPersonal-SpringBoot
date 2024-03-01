package com.groundbooking.groundbookingmonolythicapp.Services;

import com.groundbooking.groundbookingmonolythicapp.Entities.ExpiredGroundBookings;

import java.util.List;


public interface ExpiredGroundBookingsService {
    public int deleteExpiredGroundBookings();
    public int addExpiredGroundBookings(List<ExpiredGroundBookings> expiredGroundsBookings);
}
