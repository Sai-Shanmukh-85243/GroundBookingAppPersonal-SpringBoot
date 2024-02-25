package com.groundbooking.groundbookingmonolythicapp.Services;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;

import java.util.List;

public interface BookingDetailsService {
    public String validateSlot(BookingDetails bookingDetails);
    public BookingDetails addBooking(BookingDetails bookingDetails);
    public List<BookingDetails> getAllBookingByUsername(String username);
}
