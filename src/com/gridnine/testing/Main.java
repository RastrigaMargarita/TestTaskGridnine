package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("//All flights");
        System.out.println(flightList.toString());

        System.out.println("//Already flown");
        System.out.println(FlightFilter.beforeNow(flightList).toString());

        System.out.println("//Arrival before departure");
        System.out.println(FlightFilter.arrivalBeforeDeparture(flightList).toString());

        System.out.println("//Long time waiting");
        System.out.println(FlightFilter.longTimeWaiting(flightList).toString());
    }
}
