package com.veena.bookmyshow.models;

import lombok.Data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Data
@Entity
public class Theatre extends BaseModel{
    private String name;
    private String address;

    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}

