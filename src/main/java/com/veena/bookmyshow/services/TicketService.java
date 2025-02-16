package com.veena.bookmyshow.services;

import com.veena.bookmyshow.models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception;
}