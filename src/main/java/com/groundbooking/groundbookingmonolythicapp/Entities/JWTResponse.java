package com.groundbooking.groundbookingmonolythicapp.Entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JWTResponse {
    private String token;
    private String username;
    private String role;
}
