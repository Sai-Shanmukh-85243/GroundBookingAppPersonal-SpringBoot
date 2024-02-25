package com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginExpiredException extends RuntimeException{
    String message;

    public LoginExpiredException(String message){
        this.message=message;
    }

}
