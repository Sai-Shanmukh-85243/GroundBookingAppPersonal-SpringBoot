package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;


import com.groundbooking.groundbookingmonolythicapp.Entities.ActiveBookingsOfDeletedGround;
import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;
import com.groundbooking.groundbookingmonolythicapp.Entities.GroundDetails;
import com.groundbooking.groundbookingmonolythicapp.Respositories.ActiveBookingOfDeletedGroundRepository;
import com.groundbooking.groundbookingmonolythicapp.Services.ActiveBookingOfDeletedGroundService;
import com.groundbooking.groundbookingmonolythicapp.Services.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActiveBookingsOfDeletedGroundServiceImpl implements ActiveBookingOfDeletedGroundService {

    @Autowired
    private ActiveBookingOfDeletedGroundRepository activeBookingOfDeletedGroundRepository;
    @Lazy       //Added @Lazy to solve the issue of circular dependency
    @Autowired
    private BookingDetailsService bookingDetailsService;

    @Override
    public void addActiveBookingOfDeletedGround(GroundDetails groundDetails) {
        if (groundDetails != null) {
            List<BookingDetails> activeBookingsOnGround = bookingDetailsService.getBookingByGroundDetails(groundDetails.getGround_id());
            List<ActiveBookingsOfDeletedGround> activeBookingsOfDeletedGround = new ArrayList<ActiveBookingsOfDeletedGround>();
            for (BookingDetails bookingDetails : activeBookingsOnGround) {
                ActiveBookingsOfDeletedGround temp = new ActiveBookingsOfDeletedGround();
                temp.setGroundName(bookingDetails.getGroundDetails().getGroundName());
                temp.setGroundLocation(bookingDetails.getGroundDetails().getGroundLocation());
                temp.setGroundPrice(bookingDetails.getGroundDetails().getPrice().toString());
                temp.setGroundOwner(bookingDetails.getGroundDetails().getAddedBy());
                temp.setGroundDeletedOn(LocalDate.now());
                temp.setGroundDeletedTime(LocalTime.now());
                temp.setBookedBy(bookingDetails.getUsername());
                temp.setBookedFor(bookingDetails.getDate());
                temp.setBookingStartTime(bookingDetails.getStartTime());
                temp.setBookingEndTime(bookingDetails.getEndTime());
                temp.setBookedOn(bookingDetails.getBookedOn());
                temp.setEmailOfBookedBy(bookingDetails.getUserDetails().getEmail());
                temp.setContactOfBookedBy(bookingDetails.getUserDetails().getMobile_number());
                activeBookingsOfDeletedGround.add(temp);
                bookingDetailsService.deleteBookings(bookingDetails);
            }
            activeBookingOfDeletedGroundRepository.saveAll(activeBookingsOfDeletedGround);

        }
    }
}
