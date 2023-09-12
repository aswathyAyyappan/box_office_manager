package com.restive.boxoffice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class TicketTransactionOutputDTO {
    private Long transactionId;
    private List<TicketDTO> tickets;
    private BigDecimal totalCost;
}
