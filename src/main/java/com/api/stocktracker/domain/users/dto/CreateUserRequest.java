package com.api.stocktracker.domain.users.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String password;
    
    private String confirmPassword;
}
