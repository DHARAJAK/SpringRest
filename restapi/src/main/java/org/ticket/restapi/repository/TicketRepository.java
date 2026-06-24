package org.ticket.restapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticket.restapi.domain.Status;
import org.ticket.restapi.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	boolean existsByPhoneNumber(long phoneNumber);

	Optional<Ticket> findByPhoneNumber(long phoneNumber);

	List<Ticket> findByStatus(Status status);
}