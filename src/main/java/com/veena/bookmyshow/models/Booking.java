package com.veena.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @ManyToOne
    private MovieShow movieShow;
    private double amount;
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;
    private Date bookingDate;
    @OneToMany
    private List<Payment> payments;
    private BookingStatus status;
}