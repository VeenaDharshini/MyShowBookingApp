package com.veena.bookmyshow.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veena.bookmyshow.models.Seat;
import com.veena.bookmyshow.models.SeatStatus;
import com.veena.bookmyshow.models.Show;
import com.veena.bookmyshow.models.ShowSeat;
import com.veena.bookmyshow.models.Ticket;
import com.veena.bookmyshow.models.TicketStatus;
import com.veena.bookmyshow.models.User;
import com.veena.bookmyshow.repositories.SeatsRepository;
import com.veena.bookmyshow.repositories.ShowRepository;
import com.veena.bookmyshow.repositories.ShowSeatRepository;
import com.veena.bookmyshow.repositories.TicketRepository;
import com.veena.bookmyshow.repositories.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

    private final SeatsRepository seatsRepo;
    private final ShowRepository showRepo;
    private final ShowSeatRepository showSeatRepo;
    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;

    @Autowired
    public TicketServiceImpl(
            SeatsRepository seatsRepo,
            ShowRepository showRepo,
            ShowSeatRepository showSeatRepo,
            TicketRepository ticketRepo,
            UserRepository userRepo
    ){
        this.seatsRepo = seatsRepo;
        this.showRepo = showRepo;
        this.showSeatRepo = showSeatRepo;
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception{
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        List<Seat> bookedSeats = new ArrayList<>();
        Set<Show> showSet = new HashSet<>();
        for(Integer showSeatId : showSeatIds){
            ShowSeat showSeat = showSeatRepo
                    .findById(showSeatId)
                    .orElseThrow(()->new RuntimeException("Invalid Seat for the given show"));
            if(!showSeat.getStatus().equals(SeatStatus.AVAILABLE)){
                throw new RuntimeException(showSeatId + " seat is not available!");
            }
            showSeat.setStatus(SeatStatus.BLOCKED);
            bookedSeats.add(showSeat.getSeat());
            showSet.add(showSeat.getShow());
        }

        if(showSet.size()!=1){
            throw new RuntimeException("Different shows!");
        }

        if(bookedSeats.size() != showSeatIds.size()){
            throw new RuntimeException("Something went wrong in booking!");
        }

        Show showBooked = showSet.stream().collect(Collectors.toList()).get(0);

        Ticket newTicket = new Ticket();
        newTicket.setShow(showBooked);
        newTicket.setSeats(bookedSeats);
        newTicket.setTimeOfBooking(new Date());
        newTicket.setUser(user);
        newTicket.setStatus(TicketStatus.UNPAID);

        return ticketRepo.save(newTicket);
    }
}

