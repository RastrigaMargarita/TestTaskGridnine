package com.gridnine.testing;

import com.gridnine.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilter {
    LocalDateTime currentMoment = LocalDateTime.now();
    int timeWaiting = 2;

    List<Flight> filter(List<Flight> flightList);
}
