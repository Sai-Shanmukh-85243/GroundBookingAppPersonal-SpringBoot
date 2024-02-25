package com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNotFound extends RuntimeException{
    String entity;
    String message;
    String value;

    public DataNotFound(String entity,String message,String value) {
        this.entity = entity;
        this.message = message;
        this.value = value;
    }
}
