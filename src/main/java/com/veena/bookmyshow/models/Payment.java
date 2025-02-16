package com.veena.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    @Enumerated(value = EnumType.STRING)
    private PaymentProvider paymentProvider;
    private String referenceNumber;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String amount;
}
