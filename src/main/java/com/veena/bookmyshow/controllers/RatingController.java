package com.veena.bookmyshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/rate")
    public ResponseEntity<RateMovieResponseDto> rateMovie(@RequestBody RateMovieRequestDto requestDto) {
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try {
            Rating rating = ratingService.rateMovie(requestDto.getUserId(), requestDto.getMovieId(), requestDto.getRating());
            responseDto.setRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/average/{movieId}")
    public ResponseEntity<GetAverageMovieResponseDto> getAverageMovieRating(@PathVariable("movieId") GetAverageMovieRequestDto requestDto) {
        GetAverageMovieResponseDto responseDto = new GetAverageMovieResponseDto();
        try {
            double ratingAvg = ratingService.getAverageRating(requestDto.getMovieId());
            responseDto.setAverageRating(ratingAvg);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
