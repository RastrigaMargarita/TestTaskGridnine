package com.gridnine.testing;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testOfFlightFilter {
    private static LocalDateTime threeDaysFromNow;

    @BeforeClass
    public static void createArrayHandler() {
        threeDaysFromNow = LocalDateTime.now().plusDays(3);
    }

    @Test
    public void testbeforeNow1() {
        Assert.assertEquals(new ArrayList<Flight>(),
                FlightFilter.beforeNow(getSimpleFlightArray(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
    }

    @Test
    public void testbeforeNow2() {
        List<Flight> flightList = getSimpleFlightArray(threeDaysFromNow.minusDays(5), threeDaysFromNow.plusHours(2));
        Assert.assertArrayEquals(new List[]{flightList}, new List[]{FlightFilter.beforeNow(flightList)});
    }

    @Test
    public void testArrivalBeforeDeparture1() {
        Assert.assertEquals(new ArrayList<Flight>(),
                FlightFilter.arrivalBeforeDeparture(getSimpleFlightArray(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
    }

    @Test
    public void testArrivalBeforeDeparture2() {
        List<Flight> flightList = getSimpleFlightArray(threeDaysFromNow, threeDaysFromNow.minusHours(6));
        Assert.assertArrayEquals(new List[]{flightList}, new List[]{FlightFilter.arrivalBeforeDeparture(flightList)});
    }

    @Test
    public void testlongTimeWaiting1() {
        Assert.assertEquals(new ArrayList<Flight>(),
                FlightFilter.longTimeWaiting(getSimpleFlightArray(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
    }

    @Test
    public void testlongTimeWaiting2() {
        List<Flight> flightList = getLongFlightArray(threeDaysFromNow, threeDaysFromNow.minusHours(6));
        Assert.assertArrayEquals(new List[]{flightList}, new List[]{FlightFilter.longTimeWaiting(flightList)});
    }

    private List<Flight> getLongFlightArray(LocalDateTime start, LocalDateTime finish) {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(start, finish));
        segmentList.add(new Segment(start.plusDays(1), finish.plusDays(1)));
        Flight flight = new Flight(segmentList);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);
        return flightList;
    }

    private List<Flight> getSimpleFlightArray(LocalDateTime start, LocalDateTime finish) {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(start, finish));
        Flight flight = new Flight(segmentList);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);
        return flightList;
    }
}
