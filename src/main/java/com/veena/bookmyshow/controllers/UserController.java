package com.veena.bookmyshow.controllers;

import com.veena.bookmyshow.dtos.LoginRequestDto;
import com.veena.bookmyshow.dtos.LoginResponseDto;
import com.veena.bookmyshow.dtos.SignupUserRequestDTO;
import com.veena.bookmyshow.dtos.SignupUserResponseDTO;
import com.veena.bookmyshow.models.User;
import com.veena.bookmyshow.dtos.ResponseStatus;
import com.veena.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupUserResponseDTO> signupUser(@RequestBody SignupUserRequestDTO requestDTO) {
        SignupUserResponseDTO responseDTO = new SignupUserResponseDTO();
        try {
            User user = userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setName(user.getName());
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try{
            boolean isLoggedIn = userService.login(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setLoggedIn(isLoggedIn);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

}

