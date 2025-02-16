package com.veena.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String screenName;
    @OneToMany
    private List<Seat> seats;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Feature> featuresSupported;
}