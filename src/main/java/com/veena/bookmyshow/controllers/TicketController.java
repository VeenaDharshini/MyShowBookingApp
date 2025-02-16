package com.veena.bookmyshow.controllers;

import com.veena.bookmyshow.models.Ticket;
import com.veena.bookmyshow.dtos.BookTicketResponseDTO;
import com.veena.bookmyshow.dtos.BookTicketRequestDTO;
import com.veena.bookmyshow.dtos.ResponseStatus;

import com.veena.bookmyshow.services.TicketService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping("/book")
    public BookTicketResponseDTO bookTicket(@RequestBody BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
        try{
            Ticket ticketBooked = ticketService.bookTicket(requestDTO.getShowSeatIds(), requestDTO.getUserId());
            responseDTO.setTicket(ticketBooked);
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
