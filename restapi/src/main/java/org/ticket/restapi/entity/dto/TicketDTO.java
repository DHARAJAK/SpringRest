package org.ticket.restapi.entity.dto;

import java.time.LocalDateTime;

import org.ticket.restapi.domain.Category;
import org.ticket.restapi.domain.Status;

public class TicketDTO {

	private int id;

	private long phoneNumber;

	private Category category;

	private String issueDetails;

	private String resolutionDetails;

	private Status status;

	private LocalDateTime createDateTime;

	private LocalDateTime resolutionDateTime;

	public TicketDTO() {
		super();
	}

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

	@Override
	public String toString() {
		return "TicketDTO [id=" + id + ", phoneNumber=" + phoneNumber + ", category=" + category + ", issueDetails="
				+ issueDetails + ", resolutionDetails=" + resolutionDetails + ", status=" + status + ", createDateTime="
				+ createDateTime + ", resolutionDateTime=" + resolutionDateTime + "]";
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

}
