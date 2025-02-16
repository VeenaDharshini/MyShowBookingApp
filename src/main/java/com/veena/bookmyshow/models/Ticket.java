package com.veena.bookmyshow.models;

import lombok.Data;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Data
@Entity
public class Ticket extends BaseModel{

    @ManyToOne
    private Show show;

    @ManyToMany
    List<Seat> seats;

    private Date timeOfBooking;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
