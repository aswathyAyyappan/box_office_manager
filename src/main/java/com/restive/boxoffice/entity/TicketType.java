package com.restive.boxoffice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket_type")
@Data
@NoArgsConstructor
public class TicketType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    private BigDecimal basePrice;
}
