package com.veena.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.veena.bookmyshow.models.Seat;
import com.veena.bookmyshow.models.ShowSeat;


@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    Optional<ShowSeat> findBySeat(Seat seat);
}
