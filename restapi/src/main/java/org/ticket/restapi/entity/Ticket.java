package org.ticket.restapi.entity;

import java.time.LocalDateTime;

import org.ticket.restapi.domain.Category;
import org.ticket.restapi.domain.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "phone_number")
	private long phoneNumber;

	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(name = "issue_details")
	private String issueDetails;

	@Column(name = "resolution_details")
	private String resolutionDetails;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "create_date_time")
	private LocalDateTime createDateTime;

	@Column(name = "resolution_date_time")
	private LocalDateTime resolutionDateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getIssueDetails() {
		return issueDetails;
	}

	public void setIssueDetails(String issueDetails) {
		this.issueDetails = issueDetails;
	}

	public String getResolutionDetails() {
		return resolutionDetails;
	}

	public void setResolutionDetails(String resolutionDetails) {
		this.resolutionDetails = resolutionDetails;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getResolutionDateTime() {
		return resolutionDateTime;
	}

	public void setResolutionDateTime(LocalDateTime resolutionDateTime) {
		this.resolutionDateTime = resolutionDateTime;
	}

	public Ticket() {
		super();
	}

}
