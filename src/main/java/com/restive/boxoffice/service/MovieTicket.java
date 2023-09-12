package com.restive.boxoffice.service;

import com.restive.boxoffice.dto.CustomerDTO;
import com.restive.boxoffice.dto.TicketDTO;
import com.restive.boxoffice.dto.TicketTransactionInputDTO;
import com.restive.boxoffice.dto.TicketTransactionOutputDTO;
import com.restive.boxoffice.entity.AgeCategory;
import com.restive.boxoffice.repository.AgeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MovieTicket {

    private final Ticket adultTicket;
    private final Ticket childrenTicket;
    private final Ticket seniorTicket;
    private final Ticket teenTicket;
    private final AgeCategoryRepository ageCategoryRepository;

    @Autowired
    public MovieTicket(@Qualifier("adultTicket") Ticket adultTicket,
                       @Qualifier("childrenTicket") Ticket childrenTicket,
                       @Qualifier("seniorTicket") Ticket seniorTicket,
                       @Qualifier("teenTicket") Ticket teenTicket,
                       AgeCategoryRepository ageCategoryRepository) {
        this.adultTicket = adultTicket;
        this.childrenTicket = childrenTicket;
        this.seniorTicket = seniorTicket;
        this.teenTicket = teenTicket;
        this.ageCategoryRepository = ageCategoryRepository;
    }

    public TicketTransactionOutputDTO bookTicket(TicketTransactionInputDTO inputDTO) {
        Long transactionId = inputDTO.getTransactionId();
        List<CustomerDTO> customers = inputDTO.getCustomers();
        BigDecimal totalCost = BigDecimal.ZERO;
        // Initialize ticket type counter
        Map<String, Integer> ticketTypeCounts = new HashMap<>();

        //Process each customer to get the customer count in each type
        for (CustomerDTO customer : customers) {
            String ticketType = calculateTicketType(customer.getAge());
            ticketTypeCounts.put(ticketType, ticketTypeCounts.getOrDefault(ticketType, 0) + 1);
        }

        //process the Ticket type list to calculate the total amount for each type.
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for(Map.Entry<String, Integer> ticketEntry : ticketTypeCounts.entrySet() ){
            String ticketType = ticketEntry.getKey();
            int count = ticketEntry.getValue();
            BigDecimal cost = adultTicket.getPrice(count); // TO DO
            totalCost = totalCost.add(cost);
            TicketDTO ticketDTO = TicketDTO.builder()
                    .ticketType(ticketType)
                    .quantity(count)
                    .totalCost(cost)
                    .build();
            ticketDTOS.add(ticketDTO);
        }
        // Sort the tickets by ticketType
        Collections.sort(ticketDTOS,Comparator.comparing(TicketDTO::getTicketType));
        return TicketTransactionOutputDTO.builder()
                .transactionId(transactionId)
                .totalCost(totalCost)
                .tickets(ticketDTOS)
                .build();
    }

    public String calculateTicketType(int age) {
        List<AgeCategory> ageCategories = ageCategoryRepository.findAgeCategoryForAge(age);
        return ageCategories.get(0).getName();
    }

}
