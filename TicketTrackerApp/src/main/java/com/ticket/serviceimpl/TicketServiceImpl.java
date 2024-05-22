package com.ticket.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.entity.Ticket;
import com.ticket.repository.TicketRepository;
import com.ticket.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public void addTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	@Override
	public Ticket getTicketById(int id) {
		return ticketRepository.findById(id).orElse(null);
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public void updateTicket(Ticket ticket) {
		if (ticketRepository.existsById(ticket.getId())) {
			ticketRepository.save(ticket);
		}
	}

	@Override
	public void deleteTicket(int id) {
		ticketRepository.deleteById(id);
	}

	@Override
	public List<Ticket> searchTickets(String query) {
		return ticketRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
	}
}
