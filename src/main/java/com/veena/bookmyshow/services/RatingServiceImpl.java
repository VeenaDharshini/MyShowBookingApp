package com.veena.bookmyshow.services;

import com.veena.bookmyshow.models.Rating;
import com.veena.bookmyshow.repositories.MovieRepository;
import com.veena.bookmyshow.repositories.RatingRepository;
import com.veena.bookmyshow.repositories.UserRepository;
import com.veena.bookmyshow.models.Movie;
import com.veena.bookmyshow.models.User;
import java.util.Optional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veena.bookmyshow.exceptions.MovieNotFoundException;
import com.veena.bookmyshow.exceptions.UserNotFoundException;

@Service
public class RatingServiceImpl implements RatingService {

    private final MovieRepository movieRepo;
    private final RatingRepository ratingRepo;
    private final UserRepository userRepo;

    @Autowired
    public RatingServiceImpl(MovieRepository movieRepo, RatingRepository ratingRepo, UserRepository userRepo){
        this.movieRepo = movieRepo;
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
    }

    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException{
        User user = userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(()->new MovieNotFoundException("Movie Not Found"));
        Optional<Rating> rOptional = ratingRepo.findByMovieAndUser(movie, user);
        if(rOptional.isEmpty()){
            Rating ratingNew = new Rating();
            ratingNew.setMovie(movie);
            ratingNew.setRating(rating);
            ratingNew.setUser(user);
            return ratingRepo.save(ratingNew);
        }
        Rating ratingFound = rOptional.get();
        ratingFound.setRating(rating);
        return ratingRepo.save(ratingFound);
    }

    public double getAverageRating(int movieId) throws MovieNotFoundException{
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(()->new MovieNotFoundException("Movie Not Found"));
        List<Rating> ratings = ratingRepo.findAllByMovie(movie);
        if(ratings==null || ratings.isEmpty()){
            return 0.0;
        }
        double result = ratings
                .stream()
                .mapToDouble(rating -> rating.getRating())
                .average()
                .orElse(0.0);
        return result;
    }
}
