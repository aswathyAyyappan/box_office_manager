package com.restive.boxoffice.service;

import com.restive.boxoffice.entity.TicketType;
import com.restive.boxoffice.repository.TicketTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ChildrenTicketTest {
    private ChildrenTicket childrenTicket;
    private TicketTypeRepository ticketTypeRepository;

    @BeforeEach
    public void setUp(){
        //Mock TicketTypeRepository
        ticketTypeRepository = Mockito.mock(TicketTypeRepository.class);
        childrenTicket = new ChildrenTicket(ticketTypeRepository);
    }

     @Test
    public void testGetPriceWithValidTicketTypeAndNoDiscount() {
        TicketType childrenTicketType = new TicketType(Long.valueOf(1),"Children", BigDecimal.valueOf(5));
        when(ticketTypeRepository.findByName("Children")).thenReturn(childrenTicketType);

        BigDecimal price = childrenTicket.getPrice(2);

        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        assertEquals(expectedPrice, price);
    }

    @Test
    public void testGetPriceWithValidTicketTypeAndDiscount() {
        TicketType childrenTicketType = new TicketType(Long.valueOf(1),"Children", BigDecimal.valueOf(5));
        when(ticketTypeRepository.findByName("Children")).thenReturn(childrenTicketType);

        BigDecimal price = childrenTicket.getPrice(3);

        BigDecimal expectedPrice = BigDecimal.valueOf(11.25);
        assertEquals(expectedPrice, price);
    }

    @Test
    public void testGetPriceWithInvalidTicketType() {
        when(ticketTypeRepository.findByName("Children")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            childrenTicket.getPrice(3);
        });
    }

    @Test
    public void testGetPriceWithNullBasePrice() {
        TicketType childrenTicketType = new TicketType(Long.valueOf(1),"Children", null);
        when(ticketTypeRepository.findByName("Children")).thenReturn(childrenTicketType);

        assertThrows(RuntimeException.class, () -> {
            childrenTicket.getPrice(3);
        });
    }
}
