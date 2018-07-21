package ru.mera.service;

import org.junit.Test;
import ru.mera.service.aggregator.RequestsAggregator;

import static org.junit.Assert.*;

public class RequestsAggregatorTest {

    @Test
    public void testRequestAggregatorInt() {
        try {
            new RequestsAggregator(5);
            new RequestsAggregator(100);
            new RequestsAggregator(300);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testRequestIllegalValue() {
        try {
            new RequestsAggregator(-1);
            fail("Exception was expected for negative input");
        } catch (Exception e) {
        }

        try {
            new RequestsAggregator(0);
            fail("Exception was expected for zero");
        } catch (IllegalArgumentException e) {
        }
    }
}