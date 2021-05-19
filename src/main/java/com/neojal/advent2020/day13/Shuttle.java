package com.neojal.advent2020.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Shuttle {

    private int earliestDepartureTimestamp;
    private ArrayList<String> busIds;

    public Shuttle(Scanner input) {
        this.setEarliestDepartureTimestamp(input.nextInt());
        String line = input.next();
        this.setBusIds(getBusIdsFromInput(line));
    }

    private static ArrayList<String> getBusIdsFromInput(String busIdsLine) {
        ArrayList<String> busIds = new ArrayList<>();
        busIds.addAll(Arrays.asList(busIdsLine.split(",")));
        return busIds;
    }

    public int getEarliestDepartureTimestamp() {
        return earliestDepartureTimestamp;
    }

    public void setEarliestDepartureTimestamp(int earliestDepartureTimestamp) {
        this.earliestDepartureTimestamp = earliestDepartureTimestamp;
    }

    public ArrayList<String> getBusIds() {
        return busIds;
    }

    public void setBusIds(ArrayList<String> busIds) {
        this.busIds = busIds;
    }
}
