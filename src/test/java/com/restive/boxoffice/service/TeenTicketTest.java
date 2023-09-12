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

public class TeenTicketTest {
    private TeenTicket teenTicket;
    private TicketTypeRepository ticketTypeRepository;

    @BeforeEach
    public void setUp(){
        //Mock TicketTypeRepository
        ticketTypeRepository = Mockito.mock(TicketTypeRepository.class);
        //Initialize TeenTicket with mock repo
        teenTicket = new TeenTicket(ticketTypeRepository);
    }

    @Test
    public void testGetPriceWithValidData() {
        TicketType adultTicketType = new TicketType(Long.valueOf(1), "Teen", BigDecimal.valueOf(12));
        when(ticketTypeRepository.findByName("Teen")).thenReturn(adultTicketType);

        BigDecimal price = teenTicket.getPrice(3);

        BigDecimal expectedPrice = BigDecimal.valueOf(36);
        assertEquals(expectedPrice, price);
    }

    @Test
    public void testGetPriceWithNullBasePrice() {
        TicketType adultTicketType = new TicketType(Long.valueOf(1),"Teen", null);
        when(ticketTypeRepository.findByName("Teen")).thenReturn(adultTicketType);

        assertThrows(RuntimeException.class, () -> {
            teenTicket.getPrice(3);
        });
    }
}
