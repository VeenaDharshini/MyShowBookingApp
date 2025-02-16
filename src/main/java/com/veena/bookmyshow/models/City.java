package com.veena.bookmyshow.models;
import lombok.Data;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Data
@Entity
public class City extends BaseModel {
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Theatre> theatres;
}
