package com.veena.bookmyshow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veena.bookmyshow.models.Movie;
import com.veena.bookmyshow.models.Rating;
import com.veena.bookmyshow.models.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{
    Rating save(Rating ratingNew);
    List<Rating> findAllByMovie(Movie movie);
    Optional<Rating> findByMovieAndUser(Movie movie, User user);
}
