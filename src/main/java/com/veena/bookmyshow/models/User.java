package com.veena.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "'user'")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
}

