package com.restive.boxoffice.service;

import com.restive.boxoffice.entity.TicketType;
import com.restive.boxoffice.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ChildrenTicket implements Ticket {
    private final TicketTypeRepository ticketTypeRepository;

    @Autowired
    public ChildrenTicket(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public BigDecimal getPrice(int count) {
        // Find the ticket type by name
        TicketType childrenTicketType = ticketTypeRepository.findByName("Children");
        if (childrenTicketType == null) {
            throw new RuntimeException("Children's ticket type not found.");
        }
        BigDecimal basePrice = childrenTicketType.getBasePrice();

        if (basePrice == null) {
            throw new RuntimeException("Base price for Children's ticket not found");
        }

        // Define a constant for the discount rate 25%
        final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.75);
        // Check if count is more than 3 to apply discount
        if (count >= 3) {
            BigDecimal discountedPrice = basePrice.multiply(DISCOUNT_RATE);
            return discountedPrice.multiply(BigDecimal.valueOf(count));
        } else {
            return basePrice.multiply(BigDecimal.valueOf(count));
        }
    }
}
