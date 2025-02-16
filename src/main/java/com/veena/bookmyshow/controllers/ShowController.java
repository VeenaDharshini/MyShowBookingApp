package com.veena.bookmyshow.controllers;

import com.veena.bookmyshow.dtos.CreateShowRequestDTO;
import com.veena.bookmyshow.dtos.CreateShowResponseDTO;
import com.veena.bookmyshow.dtos.ResponseStatus;
import com.veena.bookmyshow.models.Show;
import com.veena.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @PostMapping("/create-show")
    public ResponseEntity<CreateShowResponseDTO> createShow(@RequestBody CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try{
            Show show = showService.createShow(requestDTO.getUserId(), requestDTO.getMovieId(), requestDTO.getScreenId(), requestDTO.getStartTime(), requestDTO.getEndTime(), requestDTO.getPricingConfig(), requestDTO.getFeatures());
            responseDTO.setShow(show);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }catch(Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
