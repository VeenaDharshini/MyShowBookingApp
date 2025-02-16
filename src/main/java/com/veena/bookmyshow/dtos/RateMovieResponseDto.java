package com.veena.bookmyshow.dtos;
import com.veena.bookmyshow.models.Rating;
import lombok.Data;

@Data
public class RateMovieResponseDto {
    private ResponseStatus responseStatus;
    private Rating rating;
}