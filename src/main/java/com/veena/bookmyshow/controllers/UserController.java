package com.veena.bookmyshow.controllers;

import com.veena.bookmyshow.dtos.LoginRequestDto;
import com.veena.bookmyshow.dtos.LoginResponseDto;
import com.veena.bookmyshow.dtos.SignupUserRequestDTO;
import com.veena.bookmyshow.dtos.SignupUserResponseDTO;
import com.veena.bookmyshow.models.User;
import com.veena.bookmyshow.dtos.ResponseStatus;
import com.veena.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public SignupUserResponseDTO signupUser(SignupUserRequestDTO requestDTO){
        SignupUserResponseDTO responseDTO = new SignupUserResponseDTO();
        try{
            User user = userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setName(user.getName());
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try{
            boolean isLoggedIn = userService.login(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setLoggedIn(isLoggedIn);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

}

