package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {
    private static LocalDateTime currentMoment = LocalDateTime.now();
    private static int timeWaiting = 2;

    //returns list of flight with departure before current moment
    public static List<Flight> beforeNow(List<Flight> flightList) {
        return flightList
                .stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(currentMoment))
                )
                .collect(Collectors.toList());
    }

    //returns list of flight with arrival before departure
    public static List<Flight> arrivalBeforeDeparture(List<Flight> flightList) {
        return flightList
                .stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    //returns list of flight with long time waiting between segments
    public static List<Flight> longTimeWaiting(List<Flight> flightList) {
        return flightList
                .stream()
                .filter(flight -> flight.getSegments().size() > 1)
                .filter(flight -> longerThenTW(flight.getSegments()))
                .collect(Collectors.toList());

    }

    private static boolean longerThenTW(List<Segment> segments) {
        long totalTime = 0;
        for (int j = 1; j < segments.size(); j++) {
            totalTime = totalTime + Duration.between(segments.get(j - 1).getArrivalDate(),
                    segments.get(j).getDepartureDate()).toHours();
        }
        return totalTime > timeWaiting;
    }
}
