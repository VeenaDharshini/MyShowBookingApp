package com.veena.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Seat extends BaseModel{
    private String name;

    @Enumerated(EnumType.STRING)
    SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    Screen screen;
}
