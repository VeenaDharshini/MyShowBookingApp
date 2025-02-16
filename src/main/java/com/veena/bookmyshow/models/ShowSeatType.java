package com.veena.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private MovieShow show;
    @ManyToOne
    private SeatType seatType;
    private double price;
}
