package com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadCredentialsException extends RuntimeException{
    String message;

    public BadCredentialsException(String s) {
        this.message = s;
    }
}

