package com.restive.boxoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TicketTransactionInputDTO {
    private Long transactionId;
    private List<CustomerDTO> customers;
}
