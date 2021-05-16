package com.neojal.advent2020.day12;

import java.util.ArrayList;

public class NavigationSystem {

    public static final char TYPE_FERRY = 'F';
    public static final char TYPE_WAYPOINT = 'W';

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
    private static final int STARTING_WAYPOINT_POSITION_X = 10;
    private static final int STARTING_WAYPOINT_POSITION_Y = 1;

    private final char navigationType;
    private final ArrayList<Character> navigationOrder = new ArrayList<>();
    private int ferryPositionX;
    private int ferryPositionY;
    private int waypointPositionX;
    private int waypointPositionY;

    public NavigationSystem(char navigationType) {
        this.navigationType = navigationType;
        initializeNavigationOrder();
        setFerryStartingPosition();
        if (navigationType == TYPE_WAYPOINT) {
            setWaypointStartingPosition();
        }
    }

    private void setFerryStartingPosition() {
        ferryPositionX = STARTING_POSITION_X;
        ferryPositionY = STARTING_POSITION_Y;
    }

    private void setWaypointStartingPosition() {
        waypointPositionX = STARTING_WAYPOINT_POSITION_X;
        waypointPositionY = STARTING_WAYPOINT_POSITION_Y;
    }

    private void initializeNavigationOrder() {
        navigationOrder.add(MOVE_EAST);
        navigationOrder.add(MOVE_SOUTH);
        navigationOrder.add(MOVE_WEST);
        navigationOrder.add(MOVE_NORTH);
    }

    public int getManhattanDistance(ArrayList<String> navigationInstructions) {
        for (String instruction : navigationInstructions) {
            processInstruction(instruction);
        }
        return calculateManhattanDistance();
    }

    private void processInstruction(String instruction) {
        char action = getAction(instruction);
        int value = getValue(instruction);

        if (isaTurnAction(action)) {
            int rotations = getRotations(value);
            if (isaTypeFerry()) {
                turnFerry(action, rotations);
            } else if (isaTypeWaypoint()) {
                rotateWaypoint(action, rotations);
            }
        } else {
            if (isaTypeFerry()) {
                action = isMoveForward(action) ? getNavigationDirection() : action;
                moveThisType(action, value);
            } else if (isaTypeWaypoint()) {
                if (isMoveForward(action)) {
                    moveFerryToTheWaypoint(value);
                } else {
                    moveThisType(action, value);
                }
            }
        }
    }

    private void moveFerryToTheWaypoint(int value) {
        ferryPositionX += waypointPositionX * value;
        ferryPositionY += waypointPositionY * value;
    }

    private boolean isaTypeFerry() {
        return navigationType == TYPE_FERRY;
    }

    private boolean isaTypeWaypoint() {
        return navigationType == TYPE_WAYPOINT;
    }

    private int calculateManhattanDistance() {
        return Math.abs(ferryPositionX - ferryPositionY);
    }

    private void moveThisType(char action, int units) {
        int directionSign = getDirectionSign(action);
        units = directionSign * units;
        setNewFerryPosition(action, units);
    }

    private void setNewFerryPosition(char action, int units) {
        if (isaMoveInNorthSouthAxis(action)) {
            moveInNorthSouthAxis(units);
        } else if (isaMoveInEastWestAxis(action)) {
            moveInEastWestAxis(units);
        }
    }

    private boolean isaMoveInNorthSouthAxis(char action) {
        return action == MOVE_NORTH || action == MOVE_SOUTH;
    }

    private boolean isaMoveInEastWestAxis(char action) {
        return action == MOVE_EAST || action == MOVE_WEST;
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

    private void turnFerry(char action, int rotations) {
        if (isActionTurnLeft(action)) {
            rotateFerryToLeft(rotations);
        } else {
            rotateFerryToRight(rotations);
        }
    }

    private boolean isActionTurnLeft(char action) {
        return action == TURN_LEFT;
    }

    private boolean isaTurnAction(char action) {
        return isActionTurnLeft(action) || isActionTurnRight(action);
    }

    private boolean isActionTurnRight(char action) {
        return action == TURN_RIGHT;
    }

    private char getNavigationDirection() {
        return navigationOrder.get(FIRST_DIRECTION);
    }

    private void rotateFerryToLeft(int rotations) {
        rotateFerry(rotations, LAST_DIRECTION, FIRST_DIRECTION);
    }

    private void rotateFerryToRight(int rotations) {
        rotateFerry(rotations, FIRST_DIRECTION, LAST_DIRECTION);
    }

    private void rotateFerry(int rotations, int originalDirectionIndex, int newDirectionIndex) {
        while (rotations-- > 0) {
            rotateFerry90degrees(originalDirectionIndex, newDirectionIndex);
        }
    }

    private int getRotations(int degrees) {
        return degrees / CARDINAL_CHANGE;
    }

    private void rotateFerry90degrees(int originalDirectionIndex, int newDirectionIndex) {
        char tmpDirection = navigationOrder.get(originalDirectionIndex);
        navigationOrder.remove(originalDirectionIndex);
        navigationOrder.add(newDirectionIndex, tmpDirection);
    }

    private void rotateWaypoint(char action, int rotations) {
        while (rotations-- > 0) {
            rotateWaypoint90Degrees(action);
        }
    }

    private void rotateWaypoint90Degrees(char action) {
        if (isActionTurnLeft(action)) {
            rotateWaypointToLeft();
        } else if (isActionTurnRight(action)) {
            rotateWaypointToRight();
        }
    }

    private void rotateWaypointToRight() {
        waypointPositionX = waypointPositionY;
        waypointPositionY = BACKWARD_DIRECTION * waypointPositionX;
    }

    private void rotateWaypointToLeft() {
        // todo: the waypoint must rotate around the ship, not around the origin.
        waypointPositionX = BACKWARD_DIRECTION * waypointPositionY;
        waypointPositionY = waypointPositionX;
    }

    private void moveInNorthSouthAxis(int value) {
        if (isaTypeFerry()) {
            ferryPositionY += value;
        } else {
            waypointPositionY += value;
        }
    }

    private void moveInEastWestAxis(int value) {
        if (isaTypeFerry()) {
            ferryPositionX += value;
        } else {
            waypointPositionX += value;
        }
    }

    private char getAction(String instruction) {
        return instruction.substring(0, 1).charAt(0);
    }

    private int getValue(String instruction) {
        return Integer.parseInt(instruction.substring(1));
    }
}