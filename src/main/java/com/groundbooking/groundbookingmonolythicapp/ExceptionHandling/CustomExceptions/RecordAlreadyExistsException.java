package com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordAlreadyExistsException extends RuntimeException{
    String entity;
    String message;
    String value;

    public RecordAlreadyExistsException(String entity,String message,String value) {
        this.entity = entity;
        this.message = message;
        this.value = value;
    }
}
