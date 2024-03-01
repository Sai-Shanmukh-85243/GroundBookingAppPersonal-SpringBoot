package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;
import com.groundbooking.groundbookingmonolythicapp.Respositories.BookingDetailsRepository;
import com.groundbooking.groundbookingmonolythicapp.Services.BookingDetailsService;
import com.groundbooking.groundbookingmonolythicapp.Services.GroundService;
import com.groundbooking.groundbookingmonolythicapp.Services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;
    @Autowired
    private UserCredentialsService userCredentialsService;
    @Autowired
    private GroundService groundService;

    @Override
    public String validateSlot(BookingDetails bookingDetails) {
        List<BookingDetails> existingDetailsList=bookingDetailsRepository.findAllByDate(bookingDetails.getDate());
        if(bookingDetails == null)
            return "ok";
        for (BookingDetails b:existingDetailsList) {
            if((bookingDetails.getStartTime().isAfter(b.getStartTime())) && (bookingDetails.getEndTime().isBefore(b.getEndTime()))){
                if(bookingDetails.getUsername().equals(b.getUsername()))
                    return "User already has another Ground booked for Slot:"+b.getStartTime()+" to "+b.getEndTime();
                if(bookingDetails.getGroundDetails().getGroundName().equals(b.getGroundDetails().getGroundName()))
                    return "Ground:"+b.getGroundDetails().getGroundName()+" has already been booked for time slot:"+b.getStartTime()+" to "+b.getEndTime();
            }
            else if((bookingDetails.getStartTime().isAfter(b.getStartTime())) && (bookingDetails.getStartTime().isBefore(b.getEndTime()))){
                if(bookingDetails.getUsername().equals(b.getUsername()))
                    return "User already has another Ground booked for Slot:"+b.getStartTime()+" to "+b.getEndTime();
                if(bookingDetails.getGroundDetails().getGroundName().equals(b.getGroundDetails().getGroundName()))
                    return "Ground:"+b.getGroundDetails().getGroundName()+" has already been booked for time slot:"+b.getStartTime()+" to "+b.getEndTime();
            }
            else if((bookingDetails.getStartTime().isBefore(b.getStartTime())) && (bookingDetails.getEndTime().isAfter(b.getStartTime()))){
                if(bookingDetails.getUsername().equals(b.getUsername()))
                    return "User already has another Ground booked for Slot:"+b.getStartTime()+" to "+b.getEndTime();
                if(bookingDetails.getGroundDetails().getGroundName().equals(b.getGroundDetails().getGroundName()))
                    return "Ground:"+b.getGroundDetails().getGroundName()+" has already been booked for time slot:"+b.getStartTime()+" to "+b.getEndTime();
            }
            else if((bookingDetails.getStartTime().isBefore(b.getStartTime()))&&(bookingDetails.getEndTime().isAfter(b.getStartTime()))){
                if(bookingDetails.getUsername().equals(b.getUsername()))
                    return "User already has another Ground booked for Slot:"+b.getStartTime()+" to "+b.getEndTime();
                if(bookingDetails.getGroundDetails().getGroundName().equals(b.getGroundDetails().getGroundName()))
                    return "Ground:"+b.getGroundDetails().getGroundName()+" has already been booked for time slot:"+b.getStartTime()+" to "+b.getEndTime();
            }
            else if((bookingDetails.getStartTime().equals(b.getStartTime()) ||
                     bookingDetails.getStartTime().isAfter(b.getStartTime())) &&
                    (bookingDetails.getEndTime().equals(b.getEndTime()) ||
                     bookingDetails.getEndTime().isBefore(b.getEndTime()))) {
                if(bookingDetails.getUsername().equals(b.getUsername()))
                    return "User already has another Ground booked for Slot:"+b.getStartTime()+" to "+b.getEndTime();
                if(bookingDetails.getGroundDetails().getGroundName().equals(b.getGroundDetails().getGroundName()))
                    return "Ground:"+b.getGroundDetails().getGroundName()+" has already been booked for time slot:"+b.getStartTime()+" to "+b.getEndTime();
            }
        }
        return "ok";
    }

    @Override
    public BookingDetails addBooking(BookingDetails bookingDetails) {
        bookingDetails.setBookedOn(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        bookingDetails.setGroundDetails(groundService.getGroundByName(bookingDetails.getGroundDetails().getGroundName()));
        bookingDetails.setUserDetails((userCredentialsService.getUserByUsername(bookingDetails.getUserDetails().getUserCredentials().getUsername()).getUserDetails()));
       // System.out.println("booking details in booking details service impl addBooking:"+bookingDetails.getUsername());
        return bookingDetailsRepository.save(bookingDetails);
    }

    @Override
    public List<BookingDetails> getAllBookingByUsername(String username) {
        System.out.println("In getAllBookingByUsername service imlementation:"+username);
        return bookingDetailsRepository.findAllByUsername(username);
    }

    @Override
    public List<BookingDetails> getAllBookings() {
        return bookingDetailsRepository.findAll();
    }

    @Override
    public void deleteBookings(BookingDetails bookingDetails) {
        bookingDetailsRepository.deleteById(bookingDetails.getBookingDetailsId());
    }
}
