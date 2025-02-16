package com.veena.bookmyshow.models;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.EqualsAndHashCode;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name = "'user'")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
}
