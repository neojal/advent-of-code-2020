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

    private static final int FORWARD_DIRECTION = 1;
    private static final int BACKWARD_DIRECTION = -1;

    private static final int CARDINAL_CHANGE = 90;
    private static final int FIRST_DIRECTION = 0;
    private static final int LAST_DIRECTION = 3;

    private static char currentDirection;
    private static int currentPositionX;
    private static int currentPositionY;

    private static FerryNavigationSystem ferryNavigationSystem;
    private final ArrayList<Character> directionsNavigationOrder = new ArrayList<>();

    private FerryNavigationSystem() {
        initializeFerryNavigationSystem();
    }

    public static FerryNavigationSystem getInstance() {
        return isFerryNavigationSystemNull() ? new FerryNavigationSystem() : ferryNavigationSystem;
    }

    private static boolean isFerryNavigationSystemNull() {
        return ferryNavigationSystem == null;
    }

    private void initializeFerryNavigationSystem() {
        directionsNavigationOrder.add(MOVE_EAST);
        directionsNavigationOrder.add(MOVE_SOUTH);
        directionsNavigationOrder.add(MOVE_WEST);
        directionsNavigationOrder.add(MOVE_NORTH);
        currentDirection = MOVE_EAST;
        currentPositionX = STARTING_POSITION_X;
        currentPositionY = STARTING_POSITION_Y;
    }

    public int getManhattanDistance(ArrayList<String> navigationInstructions) {
        int manhattanDistance = 0;
        for (String instruction : navigationInstructions) {
            manhattanDistance = processInstruction(instruction);
        }
        return manhattanDistance;
    }

    private int processInstruction(String instruction) {
        int absoluteDifferenceBetweenXandY = 0;
        char action = getAction(instruction);
        int value = getValue(instruction);

        if (isaTurnFerryAction(action)) {
            turnFerry(action, value);
        } else {
            action = isMoveForward(action) ? getCurrentFerryDirection() : action;
            moveFerry(action, value);
            absoluteDifferenceBetweenXandY = getAbsoluteDifferenceBetweenXY();
        }
        return absoluteDifferenceBetweenXandY;
    }

    private int getAbsoluteDifferenceBetweenXY() {
        return Math.abs(currentPositionX - currentPositionY);
    }

    private void moveFerry(char action, int units) {
        int directionSign = getDirectionSign(action);
        units = directionSign * units;
        setFerryNewCurrentPosition(action, units);
    }

    private void setFerryNewCurrentPosition(char action, int units) {
        switch (action) {
            case MOVE_NORTH:
            case MOVE_SOUTH:
                currentPositionY = moveInNorthSouthAxis(units);
                break;
            case MOVE_EAST:
            case MOVE_WEST:
                currentPositionX = moveInEastWestAxis(units);
                break;
        }
    }

    private boolean isMoveForward(char action) {
        return action == MOVE_FORWARD;
    }

    private int getDirectionSign(char direction) {
        return isBackwardsDirection(direction) ?
                BACKWARD_DIRECTION :
                FORWARD_DIRECTION;
    }

    private boolean isBackwardsDirection(char direction) {
        return direction == MOVE_SOUTH || direction == MOVE_WEST;
    }

    private void turnFerry(char action, int value) {
        switch (action) {
            case TURN_LEFT:
                rotateDirectionToLeft(value);
                break;
            case TURN_RIGHT:
                rotateDirectionToRight(value);
                break;
        }
    }

    private boolean isaTurnFerryAction(char action) {
        return action == TURN_LEFT || action == TURN_RIGHT;
    }

    private char getCurrentFerryDirection() {
        return currentDirection;
    }

    private void rotateDirectionToLeft(int degrees) {
        rotateDirection(degrees, LAST_DIRECTION, FIRST_DIRECTION);
    }

    private void rotateDirectionToRight(int degrees) {
        rotateDirection(degrees, FIRST_DIRECTION, LAST_DIRECTION);
    }

    private void rotateDirection(int degrees, int originalDirectionIndex, int newDirectionIndex) {
        int rotations = degrees / CARDINAL_CHANGE;
        while (rotations-- > 0) {
            char tmpDirection = directionsNavigationOrder.get(originalDirectionIndex);
            directionsNavigationOrder.remove(originalDirectionIndex);
            directionsNavigationOrder.add(newDirectionIndex, tmpDirection);
        }
        currentDirection = directionsNavigationOrder.get(FIRST_DIRECTION);
    }

    private int moveInNorthSouthAxis(int value) {
        return currentPositionY + value;
    }

    private int moveInEastWestAxis(int value) {
        return currentPositionX + value;
    }

    private char getAction(String instruction) {
        return instruction.substring(0, 1).charAt(0);
    }

    private int getValue(String instruction) {
        return Integer.parseInt(instruction.substring(1));
    }
}