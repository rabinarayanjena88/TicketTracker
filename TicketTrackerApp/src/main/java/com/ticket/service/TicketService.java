package com.ticket.service;

import java.util.List;

import com.ticket.entity.Ticket;

public interface TicketService {

	void addTicket(Ticket ticket);

	Ticket getTicketById(int id);

	List<Ticket> getAllTickets();

	void updateTicket(Ticket ticket);

	void deleteTicket(int id);

	List<Ticket> searchTickets(String query);
}
