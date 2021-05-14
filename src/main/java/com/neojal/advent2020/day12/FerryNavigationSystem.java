package com.neojal.advent2020.day12;

import java.util.ArrayList;

public class FerryNavigationSystem {

    private static final char MOVE_NORTH = 'N';
    private static final char MOVE_SOUTH = 'S';
    private static final char MOVE_EAST = 'E';
    private static final char MOVE_WEST = 'W';
    private static final char TURN_LEFT = 'L';
    private static final char TURN_RIGHT = 'R';
    private static final char MOVE_FORWARD = 'F';

    private static final int STARTING_POSITION_X = 0;
    private static final int STARTING_POSITION_Y = 0;

    private static int BACKWARD_DIRECTION = -1;

    private static char direction = MOVE_EAST;
    private static int positionX = STARTING_POSITION_X;
    private static int positionY = STARTING_POSITION_Y;

    private static FerryNavigationSystem ferryNavigationSystem;

    private FerryNavigationSystem() {}

    public static FerryNavigationSystem getInstance() {
        return isFerryNavigationSystemNull() ? new FerryNavigationSystem() : ferryNavigationSystem;
    }

    private static boolean isFerryNavigationSystemNull() {
        return ferryNavigationSystem == null;
    }

    public int getManhattanDistance(ArrayList<String> navigationInstructions) {
        for(String instruction : navigationInstructions) {
            calculateDistance(instruction);
        }
        return 0;
    }

    private void calculateDistance(String instruction) {
        char action = getAction(instruction);
        int value = getValue(instruction);

        int x = 0;
        int y = 0;

        switch (action) {
            case MOVE_NORTH:
                y = getY(value);
            case MOVE_SOUTH:
                y = getY(BACKWARD_DIRECTION*value);
            case MOVE_EAST:
                x = getX(value);
            case MOVE_WEST:
                y = getX(BACKWARD_DIRECTION*value);
            case TURN_LEFT:
                turn(TURN_LEFT, value);
            case TURN_RIGHT:
                turn(TURN_RIGHT, value);
            case MOVE_FORWARD:
            default:
        }



    }

    private void turn(char turn_direction, int degrees) {

        this.direction = turn_direction;
    }

    private int getY(int value) {
        return positionY - value;
    }

    private int getX(int value) {
        return positionX + value;
    }

    private char getAction(String instruction) {
        return instruction.substring(0,1).charAt(0);
    }

    private int getValue(String instruction) {
        return Integer.parseInt(instruction.substring(1));
    }
}