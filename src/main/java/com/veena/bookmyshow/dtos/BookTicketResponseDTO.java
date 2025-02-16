package com.veena.bookmyshow.dtos;

import com.veena.bookmyshow.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDTO {
    private ResponseStatus status;
    private Ticket ticket;
}
