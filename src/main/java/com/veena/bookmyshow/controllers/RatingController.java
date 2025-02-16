package com.veena.bookmyshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.veena.bookmyshow.dtos.GetAverageMovieRequestDto;
import com.veena.bookmyshow.dtos.GetAverageMovieResponseDto;
import com.veena.bookmyshow.dtos.RateMovieRequestDto;
import com.veena.bookmyshow.dtos.RateMovieResponseDto;
import com.veena.bookmyshow.dtos.ResponseStatus;
import com.veena.bookmyshow.models.Rating;
import com.veena.bookmyshow.services.RatingService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    // Endpoint for rating a movie
    @PostMapping("/rate")
    public RateMovieResponseDto rateMovie(@RequestBody RateMovieRequestDto requestDto) {
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try {
            Rating rating = ratingService.rateMovie(requestDto.getUserId(), requestDto.getMovieId(), requestDto.getRating());
            responseDto.setRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    // Endpoint for getting the average movie rating
    @GetMapping("/average")
    public GetAverageMovieResponseDto getAverageMovieRating(@RequestBody GetAverageMovieRequestDto requestDto) {
        GetAverageMovieResponseDto responseDto = new GetAverageMovieResponseDto();
        try {
            double ratingAvg = ratingService.getAverageRating(requestDto.getMovieId());
            responseDto.setAverageRating(ratingAvg);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
