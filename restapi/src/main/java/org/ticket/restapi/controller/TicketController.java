package org.ticket.restapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticket.restapi.entity.Ticket;
import org.ticket.restapi.entity.dto.TicketDTO;
import org.ticket.restapi.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	@Autowired
	TicketService userService;

	@PostMapping
	public ResponseEntity<?> saveTicket(@RequestBody TicketDTO dtoticket) {
		if (dtoticket == null) {
			return ResponseEntity.badRequest().body("Payload cannot be empty");
		}

		if (userService.findTicketByPhoneNumber(dtoticket.getPhoneNumber()) != null) {
			return ResponseEntity.badRequest().body("One phone number can have only one active complaint");
		}

		Ticket savedTicket = userService.createNewTicket(dtoticket);
		if (savedTicket == null) {
			return ResponseEntity.badRequest().body("Ticket could not be saved");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
	}

	@GetMapping("/{cid}")
	public ResponseEntity<?> fetchTicketById(@PathVariable("cid") int id) {
		TicketDTO dtoTicket = userService.findById(id);

		if (dtoTicket == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket ID: " + id + " does not exist");
		}

		return ResponseEntity.ok().body(dtoTicket);
	}

	@PutMapping("/{phoneNumber}")
	public ResponseEntity<?> updateTickets(@PathVariable("phoneNumber") long phoneNumber,
			@RequestBody TicketDTO dtoTicket) {
		if (userService.findTicketByPhoneNumber(phoneNumber) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record exists with this phone number");
		}

		if (dtoTicket == null) {
			return ResponseEntity.badRequest().body("No update records provided in body");
		}

		Ticket updated = userService.udpateTicketDetails(phoneNumber, dtoTicket);
		return ResponseEntity.ok().body(updated);
	}

	// 4. Fetch All Open Tickets [8 Marks]
	@GetMapping("/open")
	public ResponseEntity<?> fetchAllOpenTickets() {
		List<TicketDTO> openTicketsList = userService.fetchAllOpenTicket();

		if (openTicketsList.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204 No Content if empty
		}

		return ResponseEntity.ok().body(openTicketsList);
	}
}