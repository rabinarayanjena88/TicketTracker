package com.ticket.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.entity.Ticket;
import com.ticket.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("tickets", ticketService.getAllTickets());
		return "index";
	}

	@PostMapping("/newTicket")
	public String createTicket(@ModelAttribute Ticket ticket) {
		ticket.setCreatedDate(new Date());
		ticketService.addTicket(ticket);
		return "redirect:/";
	}

	@GetMapping("/newTicket")
	public String newTicket() {
		return "newTicket";
	}

	@PostMapping("/editTicket")
	public String redirectToEdit(@RequestParam("id") int id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "editTicket";
	}

	@PostMapping("/updateTicket")
	public String updateTicket(@ModelAttribute Ticket updatedTicket) {
		Ticket existingTicket = ticketService.getTicketById(updatedTicket.getId());

		if (existingTicket != null) {
			existingTicket
					.setTitle(updatedTicket.getTitle() != null ? updatedTicket.getTitle() : existingTicket.getTitle());
			existingTicket.setDescription(updatedTicket.getDescription() != null ? updatedTicket.getDescription()
					: existingTicket.getDescription());
			existingTicket.setContent(
					updatedTicket.getContent() != null ? updatedTicket.getContent() : existingTicket.getContent());

			existingTicket.setUpdateDate(new Date());
			ticketService.updateTicket(existingTicket);
		}
		return "redirect:/";
	}

	@PostMapping("/deleteTicket/{id}")
	public String deleteTicket(@PathVariable("id") int id) {
		ticketService.deleteTicket(id);
		return "redirect:/";
	}

	@PostMapping("/search")
	public String searchTickets(@RequestParam("query") String query, Model model) {
		List<Ticket> tickets = ticketService.searchTickets(query);
		model.addAttribute("tickets", tickets);
		return "index";
	}
}