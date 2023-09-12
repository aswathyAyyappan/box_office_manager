package com.restive.boxoffice.service;

import com.restive.boxoffice.entity.TicketType;
import com.restive.boxoffice.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TeenTicket implements Ticket {
    private final TicketTypeRepository ticketTypeRepository;

    @Autowired
    public TeenTicket(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public BigDecimal getPrice(int count) {
        TicketType adultTicketType = ticketTypeRepository.findByName("Teen");
        if (adultTicketType == null) {
            throw new RuntimeException("Adult ticket type not found");
        }
        BigDecimal basePrice = adultTicketType.getBasePrice();

        if (basePrice == null) {
            throw new RuntimeException("Base price for Adult ticket not found");
        }
        return basePrice.multiply(BigDecimal.valueOf(count));
    }
}
