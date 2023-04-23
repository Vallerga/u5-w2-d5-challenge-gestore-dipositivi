package com.gestione.dispositivi.aziendali.payload;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	
	public ErrorDetails(Date tempo_stampa, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getlDetais() {
		return details;
	}
}
