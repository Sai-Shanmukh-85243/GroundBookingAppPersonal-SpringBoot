package com.groundbooking.groundbookingmonolythicapp.Configs;

import com.groundbooking.groundbookingmonolythicapp.Entities.BookingDetails;
import com.groundbooking.groundbookingmonolythicapp.Entities.ExpiredGroundBookings;
import com.groundbooking.groundbookingmonolythicapp.Services.BookingDetailsService;
import com.groundbooking.groundbookingmonolythicapp.Services.ExpiredGroundBookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class SchedulerConfig {

    @Autowired
    private BookingDetailsService bookingDetailsService;
    @Autowired
    private ExpiredGroundBookingsService expiredGroundBookingsService;

    //@Scheduled(cron ="0 0 23 * * *") // sec min hours(clock hours) date month dayofweek   //runs every day at 11 PM
    @Scheduled(cron = "0 */5 * * * *")  //run for every 5 min
    public void DeleteExpiredGroundBookingAfterStoringARecord(){
        System.out.println("In Scheduled task ");
        List<BookingDetails> allGroundBooking = bookingDetailsService.getAllBookings();
        LocalDate todayDate = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        List<ExpiredGroundBookings> expiredGroundBookings = new ArrayList<ExpiredGroundBookings>();
        for (BookingDetails booking:allGroundBooking) {
            if(booking.getDate().isBefore(todayDate)){
                System.out.println(booking.getUsername()+":"+booking.getGroundDetails().getGroundName());
                ExpiredGroundBookings temp=new ExpiredGroundBookings();
                temp.setBookedBy(booking.getUsername());
                temp.setBookedOn(booking.getBookedOn());
                temp.setBookedFor(booking.getDate());
                temp.setBookingStartTime(booking.getStartTime());
                temp.setBookingEndTime(booking.getEndTime());
                temp.setGroundName(booking.getGroundDetails().getGroundName());
                temp.setGroundLocation(booking.getGroundDetails().getGroundLocation());
                temp.setGroundPrice(booking.getGroundDetails().getPrice().toString());
                temp.setGroundOwner(booking.getGroundDetails().getAddedBy());
                expiredGroundBookings.add(temp);
                bookingDetailsService.deleteBookings(booking);
            }
        }
        expiredGroundBookingsService.addExpiredGroundBookings(expiredGroundBookings);
    }
}
