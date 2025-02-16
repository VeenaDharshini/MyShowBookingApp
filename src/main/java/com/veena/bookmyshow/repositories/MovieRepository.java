package com.veena.bookmyshow.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veena.bookmyshow.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
    Optional<Movie> findById(int movieId);
}
