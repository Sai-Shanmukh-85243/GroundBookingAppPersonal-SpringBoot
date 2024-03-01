package com.groundbooking.groundbookingmonolythicapp.Services;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;

import java.util.List;
import java.util.UUID;

public interface BookingDetailsService {
    public String validateSlot(BookingDetails bookingDetails);
    public BookingDetails addBooking(BookingDetails bookingDetails);
    public List<BookingDetails> getAllBookingByUsername(String username);
    public List<BookingDetails> getAllBookings();
    public void deleteBookings(BookingDetails bookingDetails);
    public List<BookingDetails> getBookingByGroundDetails(UUID groundUUID);
}
