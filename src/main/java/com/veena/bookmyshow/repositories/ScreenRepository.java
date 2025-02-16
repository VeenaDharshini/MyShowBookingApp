package com.veena.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veena.bookmyshow.models.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {
}

