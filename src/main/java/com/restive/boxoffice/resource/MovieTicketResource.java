package com.restive.boxoffice.resource;

import com.restive.boxoffice.dto.TicketTransactionInputDTO;
import com.restive.boxoffice.dto.TicketTransactionOutputDTO;
import com.restive.boxoffice.exception.AgeCategoryNotFoundException;
import com.restive.boxoffice.service.MovieTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/ticket")
public class MovieTicketResource {
    private final MovieTicket movieTicket;

    @Autowired
    public MovieTicketResource(MovieTicket movieTicket) {
        this.movieTicket = movieTicket;
    }

    @PostMapping("/book")
    public ResponseEntity<TicketTransactionOutputDTO> bookTicket(
            @RequestBody TicketTransactionInputDTO inputDTO
            ) throws AgeCategoryNotFoundException {
        TicketTransactionOutputDTO ticketTransactionOutputDTO = movieTicket.bookTicket(inputDTO);
        return ResponseEntity.ok(ticketTransactionOutputDTO);
    }
}
