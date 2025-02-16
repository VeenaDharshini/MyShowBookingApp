package com.veena.bookmyshow.controllers;

import com.veena.bookmyshow.dtos.CreateShowRequestDTO;
import com.veena.bookmyshow.dtos.CreateShowResponseDTO;
import com.veena.bookmyshow.dtos.ResponseStatus;
import com.veena.bookmyshow.models.Show;
import com.veena.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowController {

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    public CreateShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try{
            Show show = showService.createShow(requestDTO.getUserId(), requestDTO.getMovieId(), requestDTO.getScreenId(), requestDTO.getStartTime(), requestDTO.getEndTime(), requestDTO.getPricingConfig(), requestDTO.getFeatures());
            System.out.println("RESULT SUCCESS: " + show);
            responseDTO.setShow(show);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            // responseDTO.setResponseStatus(null);
            System.out.println("RESULT EXCEPTIONS: " + e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
