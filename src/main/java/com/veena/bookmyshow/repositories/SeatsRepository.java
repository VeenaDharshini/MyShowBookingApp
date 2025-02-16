package com.veena.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veena.bookmyshow.models.Seat;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Integer>{

}
