package com.restive.boxoffice.service;

import java.math.BigDecimal;

public interface Ticket {
    BigDecimal getPrice(int count);
}
