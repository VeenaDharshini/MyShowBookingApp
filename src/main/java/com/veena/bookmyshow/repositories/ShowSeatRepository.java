package com.veena.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veena.bookmyshow.models.ShowSeat;


@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    Optional<ShowSeat> findBySeat(Seat seat);
}
