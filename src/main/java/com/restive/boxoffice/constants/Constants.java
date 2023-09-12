package com.restive.boxoffice.constants;

import java.math.BigDecimal;

public final class Constants {
    //Ticket Types
    public static final String ADULT_TICKET_TYPE = "Adult";
    public static final String CHILDREN_TICKET_TYPE = "Children";
    public static final String SENIOR_TICKET_TYPE = "Senior";
    public static final String TEEN_TICKET_TYPE = "Teen";

    //Discount constants
    public static final BigDecimal DISCOUNT_RATE_CHILDREN = BigDecimal.valueOf(0.75);
    public static final BigDecimal DISCOUNT_RATE_SENIOR = BigDecimal.valueOf(0.70);
}
