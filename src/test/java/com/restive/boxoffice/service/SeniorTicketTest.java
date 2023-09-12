package com.restive.boxoffice.service;

import com.restive.boxoffice.entity.TicketType;
import com.restive.boxoffice.repository.TicketTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SeniorTicketTest {
    private SeniorTicket seniorTicket;
    private TicketTypeRepository ticketTypeRepository;

    @BeforeEach
    public void setUp() {
        // Create a mock for the TicketTypeRepository
        ticketTypeRepository = mock(TicketTypeRepository.class);

        // Create an instance of the SeniorTicket class, injecting the mock repository
        seniorTicket = new SeniorTicket(ticketTypeRepository);
    }

    @Test
    public void testGetPriceWithValidTicketType() {
        TicketType adultTicketType = new TicketType(Long.valueOf(1),"Adult", BigDecimal.valueOf(25));
        when(ticketTypeRepository.findByName("Adult")).thenReturn(adultTicketType);

        BigDecimal price = seniorTicket.getPrice(3);

        BigDecimal expectedPrice = BigDecimal.valueOf(52.5);
        assertEquals(expectedPrice, price);
    }

    @Test
    public void testGetPriceWithInvalidTicketType() {
        when(ticketTypeRepository.findByName("Adult")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            seniorTicket.getPrice(3);
        });
    }

    @Test
    public void testGetPriceWithNullBasePrice() {
        TicketType adultTicketType = new TicketType(Long.valueOf(1),"Adult", null);
        when(ticketTypeRepository.findByName("Adult")).thenReturn(adultTicketType);

        assertThrows(RuntimeException.class, () -> {
            seniorTicket.getPrice(3);
        });
    }
}
