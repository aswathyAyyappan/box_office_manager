package com.restive.boxoffice.exception;

public class InvalidTicketTypeException extends RuntimeException {
    public InvalidTicketTypeException(String ticketType) {
        super("Invalid ticket type: " + ticketType);
    }
}
