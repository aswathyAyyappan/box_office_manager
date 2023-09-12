package com.restive.boxoffice.service;

import com.restive.boxoffice.dto.CustomerDTO;
import com.restive.boxoffice.dto.TicketTransactionInputDTO;
import com.restive.boxoffice.dto.TicketTransactionOutputDTO;
import com.restive.boxoffice.entity.AgeCategory;
import com.restive.boxoffice.exception.AgeCategoryNotFoundException;
import com.restive.boxoffice.repository.AgeCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieTicketTest {
    private MovieTicket movieTicket;
    private AgeCategoryRepository ageCategoryRepository;

    @BeforeEach
    public void setUp() {
        ageCategoryRepository = mock(AgeCategoryRepository.class);

        Ticket adultTicket = mock(Ticket.class);
        when(adultTicket.getPrice(anyInt())).thenReturn(BigDecimal.valueOf(25));

        Ticket childrenTicket = mock(Ticket.class);
        when(childrenTicket.getPrice(anyInt())).thenReturn(BigDecimal.valueOf(5));

        Ticket seniorTicket = mock(Ticket.class);
        when(seniorTicket.getPrice(anyInt())).thenReturn(BigDecimal.valueOf(17.5));

        Ticket teenTicket = mock(Ticket.class);
        when(teenTicket.getPrice(anyInt())).thenReturn(BigDecimal.valueOf(12));

        movieTicket = new MovieTicket(adultTicket, childrenTicket, seniorTicket, teenTicket, ageCategoryRepository);
    }

    @Test
    public void testBookTicket() throws AgeCategoryNotFoundException {
        List<CustomerDTO> customers = new ArrayList<>();
        customers.add(new CustomerDTO("John Smith",25)); // Adult
        customers.add(new CustomerDTO("Jane Doe",8));  // Children
        customers.add(new CustomerDTO("Bob Doe",70)); // Senior
        customers.add(new CustomerDTO("Billy Kid",16)); // Teen

        when(ageCategoryRepository.findAgeCategoryForAge(25)).thenReturn(List.of(new AgeCategory(1L,"Adult", 18, 64)));
        when(ageCategoryRepository.findAgeCategoryForAge(8)).thenReturn(List.of(new AgeCategory(2L,"Senior", 65, null)));
        when(ageCategoryRepository.findAgeCategoryForAge(70)).thenReturn(List.of(new AgeCategory(3L,"Teen", 11, 17)));
        when(ageCategoryRepository.findAgeCategoryForAge(16)).thenReturn(List.of(new AgeCategory(4L, "Children", 0, 10)));

        TicketTransactionInputDTO inputDTO = new TicketTransactionInputDTO(1L, customers);

        TicketTransactionOutputDTO outputDTO = movieTicket.bookTicket(inputDTO);

        assertNotNull(outputDTO);
        assertEquals(4, outputDTO.getTickets().size());
        assertEquals(BigDecimal.valueOf(59.5), outputDTO.getTotalCost());
    }

}
