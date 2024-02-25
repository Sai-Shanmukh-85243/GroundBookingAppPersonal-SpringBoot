package com.groundbooking.groundbookingmonolythicapp.Controllers;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.DataNotFound;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.BookingAlreadyExists;
import com.groundbooking.groundbookingmonolythicapp.Services.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingDetailsService bookingDetailsService;
    @PostMapping("/addbooking")
    public ResponseEntity<BookingDetails> addBooking(@RequestBody BookingDetails bookingDetails){
        String validateSlotMessage=bookingDetailsService.validateSlot(bookingDetails);
        if(validateSlotMessage.equals("ok"))
            return ResponseEntity.status(HttpStatus.OK).body(bookingDetailsService.addBooking(bookingDetails));
        throw new BookingAlreadyExists(validateSlotMessage);
    }

    @GetMapping("/mybookings")
    public ResponseEntity<List<BookingDetails>> getAllBookingByUsername(@RequestParam("username") String username){

        List<BookingDetails> booking=bookingDetailsService.getAllBookingByUsername(username);

        if(booking.size()==0){
            throw new DataNotFound("No bookings","registered ","username:"+username);
        }
        else{
            //Making All the images null for now because in react native it is taking a lot of time to get images and hence metro was disconnecting. Will remove this later
//            booking.forEach((x)->{
//                x.getGroundDetails().setImage(null);
//            });
            return ResponseEntity.status(HttpStatus.OK).body(booking);
        }
    }
}
