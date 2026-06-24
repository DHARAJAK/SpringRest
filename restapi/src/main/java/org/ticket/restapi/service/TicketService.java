package org.ticket.restapi.service;

import java.util.List;

import org.ticket.restapi.entity.Ticket;
import org.ticket.restapi.entity.dto.TicketDTO;

public interface TicketService {
	Ticket createNewTicket(TicketDTO dtoTicket);

	Ticket udpateTicketDetails(long phoneNumber, TicketDTO dtoTicket);

	TicketDTO findById(int id);

	public Ticket findTicketByPhoneNumber(long phoneNumber);

	public List<TicketDTO> fetchAllOpenTicket();
}
