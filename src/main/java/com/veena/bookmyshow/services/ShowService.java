package com.veena.bookmyshow.services;

import com.veena.bookmyshow.exceptions.*;
import com.veena.bookmyshow.models.Feature;
import com.veena.bookmyshow.models.SeatType;
import com.veena.bookmyshow.models.Show;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

public interface ShowService {
    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime, List<Pair<SeatType, Double>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException;
}

