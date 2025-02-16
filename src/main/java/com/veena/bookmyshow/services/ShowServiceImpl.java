package com.veena.bookmyshow.services;

import com.veena.bookmyshow.models.Show;
import com.veena.bookmyshow.models.ShowSeat;
import com.veena.bookmyshow.models.User;
import com.veena.bookmyshow.models.UserType;
import com.veena.bookmyshow.repositories.MovieRepository;
import com.veena.bookmyshow.repositories.ScreenRepository;
import com.veena.bookmyshow.repositories.SeatTypeShowRepository;
import com.veena.bookmyshow.repositories.SeatsRepository;
import com.veena.bookmyshow.repositories.ShowRepository;
import com.veena.bookmyshow.repositories.ShowSeatRepository;
import com.veena.bookmyshow.repositories.UserRepository;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.veena.bookmyshow.exceptions.*;
import com.veena.bookmyshow.models.SeatType;
import com.veena.bookmyshow.models.SeatTypeShow;
import com.veena.bookmyshow.models.Feature;
import com.veena.bookmyshow.models.Movie;
import com.veena.bookmyshow.models.Screen;
import com.veena.bookmyshow.models.ScreenStatus;
import com.veena.bookmyshow.models.Seat;
import com.veena.bookmyshow.models.SeatStatus;

@Service
public class ShowServiceImpl implements ShowService {
    private final MovieRepository movieRepo;
    private final ScreenRepository screenRepo;
    private final SeatsRepository seatsRepo;
    private final SeatTypeShowRepository seatTypeShowRepo;
    private final ShowRepository showRepo;
    private final ShowSeatRepository showSeatRepo;
    private final UserRepository userRepo;

    @Autowired
    public ShowServiceImpl(MovieRepository movieRepo,
                           ScreenRepository screenRepo,
                           SeatsRepository seatsRepo,
                           SeatTypeShowRepository seatTypeShowRepo,
                           ShowRepository showRepo,
                           ShowSeatRepository showSeatRepo,
                           UserRepository userRepo){
        this.movieRepo = movieRepo;
        this.screenRepo = screenRepo;
        this.seatsRepo = seatsRepo;
        this.seatTypeShowRepo = seatTypeShowRepo;
        this.showSeatRepo = showSeatRepo;
        this.showRepo = showRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime, List<Pair<SeatType, Double>> pricingConfig, List<Feature> features)
            throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException{
        System.out.println("user: " + userId + " movie: " + movieId + " screen: " + screenId + " start: " + startTime + " end: " + endTime + "features: " + features);

        Movie movie = movieRepo.findById(movieId).orElseThrow(()->new MovieNotFoundException("Movie Not Found"));
        User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
        if(!user.getUserType().equals(UserType.ADMIN)){
            System.out.println("EXCEPTION ADMIN ACCESS");
            throw new UnAuthorizedAccessException("Admin can only create show");
        }
        if(startTime==null || endTime==null){
            System.out.println("EXCEPTION START TIME");
            throw new InvalidDateException("Date is not valid");
        }
        if(startTime.after(endTime)){
            System.out.println("EXCEPTION END TIME");
            throw new InvalidDateException("Start time is greater than end time");
        }
        if(startTime.before(new Date(System.currentTimeMillis()))){
            throw new InvalidDateException("Start time is not valid");
        }
        Screen screen = screenRepo.findById(screenId).orElseThrow(()->new ScreenNotFoundException("Screen Not Found"));
        if(!screen.getStatus().equals(ScreenStatus.OPERATIONAL)){
            System.out.println("EXCEPTION OPERATIONAL SCREEN");
            throw new ScreenNotFoundException("Screen is not operational");
        }
        List<Feature> screenSupported = screen.getFeatures();
        for(Feature feature : features){
            if(!screenSupported.contains(feature)){
                System.out.println("EXCEPTION FEATURES");
                throw new FeatureNotSupportedByScreen("Feature Not Supported By Screen");
            }
        }
        Show showNew = new Show();
        showNew.setMovie(movie);
        showNew.setScreen(screen);
        showNew.setStartTime(startTime);
        showNew.setEndTime(endTime);
        showNew.setFeatures(features);
        Show savedShow = showRepo.save(showNew);
        for(Pair<SeatType, Double> seatTypeShow : pricingConfig){
            SeatTypeShow seatTypeShowNew = new SeatTypeShow();
            seatTypeShowNew.setPrice(seatTypeShow.getSecond());
            seatTypeShowNew.setSeatType(seatTypeShow.getFirst());
            seatTypeShowNew.setShow(savedShow);
            seatTypeShowRepo.save(seatTypeShowNew);
        }

        for(Seat seat : screen.getSeats()){
            ShowSeat showSeatNew = new ShowSeat();
            showSeatNew.setSeat(seat);
            showSeatNew.setShow(savedShow);
            showSeatNew.setStatus(SeatStatus.AVAILABLE);
            showSeatRepo.save(showSeatNew);
        }

        System.out.println(savedShow);
        return savedShow;
    }

}
