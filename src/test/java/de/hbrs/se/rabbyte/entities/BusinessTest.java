package de.hbrs.se.rabbyte.entities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessTest {

    private final Business business = new Business();

    private static final String BUSINESS_NAME = "Mustermann GMBH";
    @BeforeMethod
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