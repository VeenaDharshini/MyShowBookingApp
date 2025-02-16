package com.veena.bookmyshow.models;

import lombok.Data;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Screen extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    private ScreenStatus status;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Feature> features;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
