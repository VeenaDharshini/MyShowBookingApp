package com.veena.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veena.bookmyshow.models.SeatTypeShow;

@Repository
public interface SeatTypeShowRepository extends JpaRepository<SeatTypeShow, Integer>{

}