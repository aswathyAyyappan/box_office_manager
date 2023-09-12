package com.restive.boxoffice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TicketDTO {
    private String ticketType;
    private int quantity;
    private BigDecimal totalCost;
}
