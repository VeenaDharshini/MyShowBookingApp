package com.veena.bookmyshow.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {
    private boolean loggedIn;
    private ResponseStatus responseStatus;
}
