package com.groundbooking.groundbookingmonolythicapp.Entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    String message;
    Boolean success;
}
