package com.restive.boxoffice.repository;

import com.restive.boxoffice.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    TicketType findByName(String name);
}
