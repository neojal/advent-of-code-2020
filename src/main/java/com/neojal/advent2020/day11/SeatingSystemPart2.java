package com.neojal.advent2020.day11;

public class SeatingSystemPart2 {

    private static final char SEAT_FREE = 'L';
    private static final char SEAT_OCCUPIED = '#';
    private static final char SEAT_TO_FREE = '-';
    private static final char SEAT_TO_OCCUPY = '+';
    private static final char EMPTY_SPACE = '.';

    public int getOccupiedSeats(char[][] seatingArea) {

        int occupiedSeats = 0;

        //setAllOccupiedSeats( seatingArea );

        int repetitions = 1000;

        while (--repetitions > 0) {

            evaluateSeats(seatingArea);
            occupiedSeats = printSeatingArea(seatingArea);

            System.out.println(occupiedSeats);
        }

        return occupiedSeats;
    }

    /**
     * @param seatingArea If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * @return
     */
    private void evaluateSeats(char[][] seatingArea) {

        for (int i = 0; i < seatingArea.length; i++) {
            for (int j = 0; j < seatingArea[i].length; j++) {

                // will free a occupied seat if 5 or more visible occupied seats in the view
                if (
                        seatingArea[i][j] == SEAT_OCCUPIED &&
                                getVisibleOccupiedSeats(seatingArea, i, j) >= 5) {

                    seatingArea[i][j] = SEAT_TO_FREE;
                }

                // will occupy a empty seat if
                if (
                        seatingArea[i][j] == SEAT_FREE &&
                                getVisibleOccupiedSeats(seatingArea, i, j) < 5) {

                    seatingArea[i][j] = SEAT_TO_OCCUPY;
                }
            }
        }

        printSeatingArea(seatingArea);
        freeSeatsMarkedToFree(seatingArea);
    }

    /**
     * @param seatingArea
     * @param i
     * @param j
     * @return the number of occupied seats in 8 directions view from seatingArea[i][j]
     */
    private int getVisibleOccupiedSeats(char[][] seatingArea, int i, int j) {

        int maxLineIndex = seatingArea.length - 1;
        int maxSpacesIndex = seatingArea[i].length - 1;

        int visibleOccupiedSeats = 0;

        char currentPositionChar = seatingArea[i][j];

        int partialVisibleSeat = 0;

        // dir up
        for (int up = i - 1; up >= 0; up--) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[up][j])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }
        // dir down
        for (int down = i + 1; down <= maxLineIndex; down++) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[down][j])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }

        // dir left
        for (int left = j - 1; left >= 0; left--) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[i][left])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }

        // dir right
        for (int right = j + 1; right <= maxSpacesIndex; right++) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[i][right])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }

        // dir up-left
        for (int up = i - 1, left = j - 1; up >= 0 && left >= 0; up--, left--) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[up][left])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }
        // dir up-right
        for (int up = i - 1, right = j + 1; up >= 0 && right <= maxSpacesIndex; up--, right++) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[up][right])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }
        // dir down-left
        for (int down = i + 1, left = j - 1; down <= maxLineIndex && left >= 0; down++, left--) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[down][left])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }
        // dir down-right
        for (int down = i + 1, right = j + 1; down <= maxLineIndex && right <= maxSpacesIndex; down++, right++) {

            if ((partialVisibleSeat = occupiedSeat(seatingArea[down][right])) >= 0) {
                visibleOccupiedSeats += partialVisibleSeat;
                break;
            }
        }

        return visibleOccupiedSeats;
    }

    /**
     * @param inspectedSeat
     * @return 1 if the inspected char is a SEAT_OCCUPIED or a SEAT_TO_FREE
     */
    private int occupiedSeat(char inspectedSeat) {

        if (inspectedSeat == SEAT_OCCUPIED) {
            //if ( inspectedSeat == SEAT_OCCUPIED ) {
            return 1;
        }
        // if the seat is free, 0 will stop the current search because is blocking view of more seats.
        else if (inspectedSeat == SEAT_FREE) {
            return 0;
        }
        // if the seat is a space or whatever allows the search to continue.
        return -1;
    }

    /**
     * @param seatingArea Frees all the seats that are marked to be free
     */
    private void freeSeatsMarkedToFree(char[][] seatingArea) {

        for (int i = 0; i < seatingArea.length; i++)
            for (int j = 0; j < seatingArea[i].length; j++) {

                if (seatingArea[i][j] == SEAT_TO_FREE)
                    seatingArea[i][j] = SEAT_FREE;

                if (seatingArea[i][j] == SEAT_TO_OCCUPY)
                    seatingArea[i][j] = SEAT_OCCUPIED;
            }
    }

    /**
     * @param seatingArea
     * @return the number of occupied seats
     */
    private int printSeatingArea(char[][] seatingArea) {
        int occupiedSeats = 0;
        // System.out.println("Current seatingArea: ");
        for (int i = 0; i < seatingArea.length; i++) {
            for (int j = 0; j < seatingArea[i].length; j++) {
                //     System.out.print(seatingArea[i][j] + " ");
                if (seatingArea[i][j] == SEAT_OCCUPIED)
                    occupiedSeats++;
            }
            //System.out.println();
        }
        return occupiedSeats;
    }

    /**
     * @param seatingArea replaces free seats with occupied seats in the char array
     * @return
     */
    private void setAllOccupiedSeats(char[][] seatingArea) {

        for (int i = 0; i < seatingArea.length; i++)
            for (int j = 0; j < seatingArea[i].length; j++)
                if (seatingArea[i][j] == SEAT_FREE)
                    seatingArea[i][j] = SEAT_OCCUPIED;
    }

    /**
     * @param matrix
     * @return a string representation of 2dom array to visualice in intellij
     */
    public static String get2DArrayPrint(char[][] matrix) {
        String output = new String();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output = output + (matrix[i][j] + "\t");
            }
            output = output + "\n";
        }
        return output;
    }
}
