package com.kavito.java8.optional;

import org.junit.Test;

import java.util.Optional;

class FlightTicketInfo {

    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

}


public class OptionalTest {

    @Test
    public void testMap() {
        FlightTicketInfo flightTicketInfo = null;

        Optional<Optional<String>> s1 = Optional.ofNullable(flightTicketInfo).map(OptionalTest::getOrderNumber);

        Optional<String> s2 = Optional.ofNullable(flightTicketInfo).map(FlightTicketInfo::getOrderNumber);

        Optional<String> s3 = Optional.ofNullable(flightTicketInfo).flatMap(OptionalTest::getOrderNumber);
    }

    private static Optional<String> getOrderNumber(FlightTicketInfo flightTicketInfo) {
        return Optional.ofNullable(flightTicketInfo).map(f -> f.getOrderNumber());
    }
}
