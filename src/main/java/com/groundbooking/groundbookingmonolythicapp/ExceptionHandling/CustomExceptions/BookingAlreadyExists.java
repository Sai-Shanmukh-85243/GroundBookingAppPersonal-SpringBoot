package com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingAlreadyExists extends RuntimeException{
    String message;

    public BookingAlreadyExists(String s) {
        this.message = s;
    }
}

