package org.ticket.restapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticket.restapi.domain.Status;
import org.ticket.restapi.entity.Ticket;
import org.ticket.restapi.entity.dto.TicketDTO;
import org.ticket.restapi.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepo;

	// FIX 1: Change existsByPhoneNumber to findByPhoneNumber
	public Ticket findTicketByPhoneNumber(long phoneNumber) {
		return ticketRepo.findByPhoneNumber(phoneNumber).orElse(null);
	}

	@Override
	public Ticket createNewTicket(TicketDTO dtoTicket) {
		if (dtoTicket != null) {
			Ticket ticket = new Ticket();
			ticket.setCategory(dtoTicket.getCategory());
			ticket.setIssueDetails(dtoTicket.getIssueDetails());
			ticket.setPhoneNumber(dtoTicket.getPhoneNumber());
			ticket.setCreateDateTime(LocalDateTime.now());
			ticket.setStatus(Status.OPEN);

			ticketRepo.save(ticket);
			return ticket;
		}
		return null;
	}

	// FIX 2: Change existsByPhoneNumber to findByPhoneNumber here too
	@Override
	public Ticket udpateTicketDetails(long phoneNumber, TicketDTO dtoTicket) {
		Ticket ticket = ticketRepo.findByPhoneNumber(phoneNumber)
				.orElseThrow(() -> new RuntimeException("Ticket not found for phone number: " + phoneNumber));

		ticket.setStatus(dtoTicket.getStatus());
		ticket.setResolutionDetails(dtoTicket.getResolutionDetails());
		ticket.setResolutionDateTime(dtoTicket.getResolutionDateTime());

		return ticketRepo.save(ticket);
	}

	@Override
	public TicketDTO findById(int id) {
		Optional<Ticket> ticket = ticketRepo.findById(id);
		if (ticket.isPresent()) {
			TicketDTO dtoTicket = new TicketDTO();
			BeanUtils.copyProperties(ticket.get(), dtoTicket);
			return dtoTicket;
		}
		return null;
	}

	@Override
	public List<TicketDTO> fetchAllOpenTicket() {

		List<Ticket> ticketList = ticketRepo.findByStatus(Status.OPEN);

		List<TicketDTO> dtoList = new ArrayList<>();

		for (Ticket t : ticketList) {
			TicketDTO dto = new TicketDTO();
			BeanUtils.copyProperties(t, dto);

			dtoList.add(dto);

		}
		return dtoList;
	}
}