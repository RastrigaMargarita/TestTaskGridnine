package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class FilterArrivalBeforeDeparture implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flightList) {
        return flightList
                .stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
