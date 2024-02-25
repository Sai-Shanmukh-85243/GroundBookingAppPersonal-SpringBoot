package com.groundbooking.groundbookingmonolythicapp.Entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTRequest {

    private String username;
    private String password;
}
