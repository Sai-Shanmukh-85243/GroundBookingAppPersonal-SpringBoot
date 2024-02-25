package com.groundbooking.groundbookingmonolythicapp.Respositories;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, UUID> {
    public List<BookingDetails> findAllByDate(LocalDate date);
//    public BookingDetails findByStartTime(LocalTime starttime);
//    public BookingDetails findByEndTime(LocalTime endtime);
    public List<BookingDetails> findAllByUsername(String name);
//    public List<BookingDetails> findAllByGroundname(String name);
}
