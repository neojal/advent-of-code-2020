package com.neojal.advent2020.day11;

public class SeatingSystem {

private static final char SEAT_FREE = 'L';
private static final char SEAT_OCCUPIED = '#';
private static final char SEAT_TO_FREE = '-';
private static final char SEAT_TO_OCCUPY = '+';

    public int getOccupiedSeats(char[][] seatingArea) {

        int occupiedSeats = 0;

        setAllOccupiedSeats( seatingArea );

        int repetitions = 84;

        while ( repetitions-- > 0 ) {

            evaluateSeats( seatingArea );
            occupiedSeats = printSeatingArea( seatingArea );
        }

        return occupiedSeats;
    }

    /**
     *
     * @param seatingArea
     *
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * @return
     */
    private void evaluateSeats(char[][] seatingArea) {

        for (int i = 0; i < seatingArea.length; i++) {
            for (int j = 0; j < seatingArea[i].length; j++) {

                // will free a occupied seat if surrounded by 4 occupied seats
                if ( seatingArea[i][j] == SEAT_OCCUPIED &&
                        areFourOccupiedAdjacentSeats(seatingArea, i, j) >= 4 ) {

                    seatingArea[i][j] = SEAT_TO_FREE;
                }

                // will occupy a empty seat if NO occupied seats adjacent to it
                if ( seatingArea[i][j] == SEAT_FREE &&
                        areFourOccupiedAdjacentSeats(seatingArea, i, j) <= 0 ) {

                    seatingArea[i][j] = SEAT_TO_OCCUPY;
                }
            }
        }

        freeSeatsMarkedToFree( seatingArea );
    }

    /**
     *
     * @param seatingArea
     * @param i
     * @param j
     * @return true if there are 4 adjacent occupied seats around seatingArea[i][j]
     */
    private int areFourOccupiedAdjacentSeats(char[][] seatingArea, int i, int j) {

        int maxLineIndex = seatingArea.length - 1;
        int maxSpacesIndex = seatingArea[i].length - 1;

        int x1 = ( i - 1 < 0 ) ? 0 : i - 1;
        int y1 = ( j - 1 < 0 ) ? 0 : j - 1;

        int x2 = ( i + 1 > maxLineIndex ) ? maxLineIndex : i + 1;
        int y2 = ( j + 1 > maxSpacesIndex ) ? maxSpacesIndex : j + 1;

        int numOfAdjacentOccupiedSeats = 0;
        for (int k = x1; k <= x2 ; k++) {
            for (int l = y1; l <= y2; l++) {

                // avoid current space and if the space is occupied
                if ( !(k == i && l == j ) &&
                        (seatingArea[k][l] == SEAT_OCCUPIED || seatingArea[k][l] == SEAT_TO_FREE ) ) {
                    numOfAdjacentOccupiedSeats++;
                }
            }
        }
        return numOfAdjacentOccupiedSeats;
    }

    /**
     *
     * @param seatingArea
     *
     * Frees all the seats that are marked to be free
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
     *
     * @param seatingArea
     * @return the number of occupied seats
     */
    private int printSeatingArea(char[][] seatingArea) {
        int occupiedSeats = 0;
        //System.out.println("Current seatingArea: ");
        for (int i = 0; i < seatingArea.length; i++) {
            for (int j = 0; j < seatingArea[i].length; j++) {
                //System.out.print(seatingArea[i][j]);
                if ( seatingArea[i][j] == SEAT_OCCUPIED )
                    occupiedSeats ++;
            }

        }
        return occupiedSeats;
    }

    /**
     *
     * @param seatingArea
     *
     * replaces free seats with occupied seats in the char array
     * @return
     */
    private void setAllOccupiedSeats(char[][] seatingArea) {

        for (int i = 0; i < seatingArea.length; i++)
            for (int j = 0; j < seatingArea[i].length; j++)
                if ( seatingArea[i][j] == SEAT_FREE)
                    seatingArea[i][j] = SEAT_OCCUPIED;
    }
}
