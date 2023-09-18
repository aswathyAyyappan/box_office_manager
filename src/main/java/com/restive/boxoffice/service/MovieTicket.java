package com.restive.boxoffice.service;

import com.restive.boxoffice.constants.Constants;
import com.restive.boxoffice.dto.CustomerDTO;
import com.restive.boxoffice.dto.TicketDTO;
import com.restive.boxoffice.dto.TicketTransactionInputDTO;
import com.restive.boxoffice.dto.TicketTransactionOutputDTO;
import com.restive.boxoffice.entity.AgeCategory;
import com.restive.boxoffice.exception.AgeCategoryNotFoundException;
import com.restive.boxoffice.exception.InvalidTicketTypeException;
import com.restive.boxoffice.repository.AgeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MovieTicket {

    private final AgeCategoryRepository ageCategoryRepository;
    private final Map<String, Ticket> ticketMap;

    @Autowired
    public MovieTicket(@Qualifier("adultTicket") Ticket adultTicket,
                       @Qualifier("childrenTicket") Ticket childrenTicket,
                       @Qualifier("seniorTicket") Ticket seniorTicket,
                       @Qualifier("teenTicket") Ticket teenTicket,
                       AgeCategoryRepository ageCategoryRepository) {
        this.ageCategoryRepository = ageCategoryRepository;
        //Creating a map to store the tickets
        this.ticketMap = new HashMap<>();
        this.ticketMap.put(Constants.ADULT_TICKET_TYPE, adultTicket);
        this.ticketMap.put(Constants.CHILDREN_TICKET_TYPE, childrenTicket);
        this.ticketMap.put(Constants.SENIOR_TICKET_TYPE, seniorTicket);
        this.ticketMap.put(Constants.TEEN_TICKET_TYPE, teenTicket);
    }

    public TicketTransactionOutputDTO bookTicket(TicketTransactionInputDTO inputDTO) throws InvalidTicketTypeException, AgeCategoryNotFoundException {
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
        for (Map.Entry<String, Integer> ticketEntry : ticketTypeCounts.entrySet()) {
            String ticketType = ticketEntry.getKey();
            int count = ticketEntry.getValue();
            // Use the ticket map to select the appropriate ticket based on ticketType
            Ticket selectedTicket = ticketMap.get(ticketType);

            if (selectedTicket == null) {
                throw new InvalidTicketTypeException("Invalid ticket type: " + ticketType);
            }
            BigDecimal cost = selectedTicket.getPrice(count);
            totalCost = totalCost.add(cost);
            TicketDTO ticketDTO = TicketDTO.builder()
                    .ticketType(ticketType)
                    .quantity(count)
                    .totalCost(cost)
                    .build();
            ticketDTOS.add(ticketDTO);
        }

        // Sort the tickets by ticketType
        ticketDTOS.sort(Comparator.comparing(TicketDTO::getTicketType));

        return TicketTransactionOutputDTO.builder()
                .transactionId(transactionId)
                .totalCost(totalCost)
                .tickets(ticketDTOS)
                .build();
    }

    public String calculateTicketType(int age) throws AgeCategoryNotFoundException {
        List<AgeCategory> ageCategories = ageCategoryRepository.findAgeCategoryForAge(age);
        if (ageCategories.isEmpty()) {
            throw new AgeCategoryNotFoundException("No age category found for age: " + age);
        }
        return ageCategories.get(0).getName();
    }

}
