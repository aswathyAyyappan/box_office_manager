package com.restive.boxoffice.service;

import com.restive.boxoffice.repository.TicketTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    }
}
