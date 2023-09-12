package com.restive.boxoffice.service;

import com.restive.boxoffice.entity.TicketType;
import com.restive.boxoffice.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SeniorTicket implements Ticket {
    private final TicketTypeRepository ticketTypeRepository;

    @Autowired
    public SeniorTicket(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public BigDecimal getPrice(int count) {
        TicketType adultTicketType = ticketTypeRepository.findByName("Adult");
        if (adultTicketType == null) {
            throw new RuntimeException("Adult ticket type not found");
        }
        BigDecimal adultBasePrice = adultTicketType.getBasePrice();
        BigDecimal adultBasePriceThirtyPercent = adultBasePrice.multiply(BigDecimal.valueOf(30/100));
        BigDecimal basePrice = adultBasePrice.subtract(adultBasePriceThirtyPercent);

        if (basePrice == null) {
            throw new RuntimeException("Base price for Adult ticket not found");
        }
        return basePrice.multiply(BigDecimal.valueOf(count));
    }
}
