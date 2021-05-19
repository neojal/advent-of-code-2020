package com.neojal.advent2020.day13;

public class ShuttleSearchImpl implements ShuttleSearch {

    private static final String OUT_OF_SERVICE_BUS_ID_HAS_BEEN_FOUND = "An out of service busId has been found.";

    @Override
    public int getEarliestBusIdMultipliedByWaitingMinutes(Shuttle shuttle) {
        int earliestTimestamp = shuttle.getEarliestDepartureTimestamp();
        int closestBusIdTimestamp = Integer.MAX_VALUE;
        int closestBusId = 0;
        for (String busId : shuttle.getBusIds()) {
            int nextTimestamp = getNextTimestamp(busId, earliestTimestamp);
            if(nextTimestamp < closestBusIdTimestamp) {
                closestBusIdTimestamp = nextTimestamp;
                closestBusId = Integer.parseInt(busId);
            }
        }
        return calculateResult(closestBusId, closestBusIdTimestamp, earliestTimestamp);
    }

    private int calculateResult(int closestBusId, int closestBusIdTimestamp, int earliestTimestamp) {
        int waitingTime = closestBusIdTimestamp - earliestTimestamp;
        return waitingTime * closestBusId;
    }

    private int getNextTimestamp(String busId, int earliestTimestamp) {
        int currentTimestamp = Integer.MAX_VALUE;
        try {
            int busIdTimestamp = Integer.parseInt(busId);
            currentTimestamp = calculateNextTimestamp(earliestTimestamp, busIdTimestamp);
        } catch (NumberFormatException e) {
            System.out.println(OUT_OF_SERVICE_BUS_ID_HAS_BEEN_FOUND);
        }
        return currentTimestamp;
    }

    private int calculateNextTimestamp(int earliestTimestamp, int busIdTimestamp) {
        return ((earliestTimestamp / busIdTimestamp) + 1) * busIdTimestamp;
    }
}