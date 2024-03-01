package com.groundbooking.groundbookingmonolythicapp.Respositories;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, UUID> {
    public List<BookingDetails> findAllByDate(LocalDate date);

    public List<BookingDetails> findAllByUsername(String name);
    //@Query("SELECT contract from Contract as contract where contract.hotel.hotel_id = :hotelId")
    @Query("SELECT bookingDetails from BookingDetails as bookingDetails where bookingDetails.groundDetails.ground_id = :groundUUID")
    public List<BookingDetails> findAllByGroundDetailsId(UUID groundUUID);
}
