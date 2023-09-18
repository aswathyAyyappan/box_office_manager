package com.restive.boxoffice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class InvalidTicketTypeException extends RuntimeException {
    private String errorMessage;
}
