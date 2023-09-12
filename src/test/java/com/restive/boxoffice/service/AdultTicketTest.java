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

public class AdultTicketTest {
    private AdultTicket adultTicket;
    private TicketTypeRepository ticketTypeRepository;

    @BeforeEach
    public void setUp(){
        //Mock TicketTypeRepository
        ticketTypeRepository = Mockito.mock(TicketTypeRepository.class);
        //Initialize AdultTicket with mock repo
        adultTicket = new AdultTicket(ticketTypeRepository);
    }

    @Test
    public void testGetPriceWithValidData() {
        TicketType adultTicketType = new TicketType(Long.valueOf(1), "Adult", BigDecimal.valueOf(25));
        when(ticketTypeRepository.findByName("Adult")).thenReturn(adultTicketType);

        BigDecimal price = adultTicket.getPrice(3);

        BigDecimal expectedPrice = BigDecimal.valueOf(75);
        assertEquals(expectedPrice, price);
    }

    @Test
    public void testGetPriceWithNullBasePrice() {
        TicketType adultTicketType = new TicketType(Long.valueOf(1),"Adult", null);
        when(ticketTypeRepository.findByName("Adult")).thenReturn(adultTicketType);

        assertThrows(RuntimeException.class, () -> {
            adultTicket.getPrice(3);
        });
    }
}
