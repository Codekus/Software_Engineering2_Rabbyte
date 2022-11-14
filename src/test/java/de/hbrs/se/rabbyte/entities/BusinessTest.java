package de.hbrs.se.rabbyte.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {

    private final Business business = new Business();

    private final String businessName = "Mustermann GMBH";
    @BeforeEach
    void setUp() {
        business.setBusinessName(businessName);
    }

    @Test
    void getBusinessName() {
        assertEquals(businessName , business.getBusinessName());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}