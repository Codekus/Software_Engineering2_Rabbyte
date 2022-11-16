package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessTest {

    private final Business business = new Business();

    private static final String BUSINESS_NAME = "Mustermann GMBH";
    @BeforeEach
    void setUp() {
        business.setBusinessName(BUSINESS_NAME);
    }

    @Test
    void getBusinessName() {
        assertEquals(BUSINESS_NAME, business.getBusinessName());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}