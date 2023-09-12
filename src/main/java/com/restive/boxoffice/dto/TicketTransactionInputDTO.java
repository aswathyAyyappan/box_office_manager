package com.restive.boxoffice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketTransactionInputDTO {
    private Long transactionId;
    private List<CustomerDTO> customers;
}
