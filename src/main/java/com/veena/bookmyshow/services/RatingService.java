package com.veena.bookmyshow.services;

import com.veena.bookmyshow.exceptions.MovieNotFoundException;
import com.veena.bookmyshow.exceptions.UserNotFoundException;
import com.veena.bookmyshow.models.Rating;

public interface RatingService {

    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException;

    public double getAverageRating(int movieId) throws MovieNotFoundException;
}
