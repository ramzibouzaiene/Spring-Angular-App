package com.ramzi.workshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
}
