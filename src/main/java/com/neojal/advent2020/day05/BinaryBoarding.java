package com.neojal.advent2020.day05;

import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;


public class BinaryBoarding {

    /**
     *
     * @param filePath
     * @return the highestSeatId calculated from each line in file.
     * @throws IOException
     *
     * From the input, each line represents:
     *  BFFBFBFLRL
     *      BFFBFBF represents row (0-127, 7 bits),
     *      LRL is column (range 0-7, 3 bits))
     *  Example:
     *      FBFBBFF = 0101100 = 44
     *      RLR = 101 = 5
     *      seatId = 44 * 8 + 5
     */
    public String getHighestSeatId(String filePath) throws IOException {

        int highestSeatId = 0;
        TreeSet<Integer> seatIdsSet = new TreeSet<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;
        while( ( line = bufferedReader.readLine()) != null) {
            String binaryRow = line.substring(0, 7).
                    replace('F', '0').replace('B', '1');
            String binaryCol = line.substring(7).
                    replace('L', '0').replace('R', '1');

            int row = Integer.valueOf(binaryRow, 2);
            int col = Integer.valueOf(binaryCol, 2);
            int seatId = (row * 8) + col;

            seatIdsSet.add(seatId);
            highestSeatId = Math.max (seatId, highestSeatId);
        }

        int missingSeatId = getMissingSeatId(seatIdsSet);

        return String.format("HighestSeatId = %d, MissingSeatId = %d", highestSeatId, missingSeatId)  ;
    }

    private int getMissingSeatId(TreeSet<Integer> seatIdsSet) {

        int size = seatIdsSet.size();
        int setPoint = seatIdsSet.first();

        int[] ids = Arrays.stream(seatIdsSet.toArray()).mapToInt(c -> (int)c).toArray();

        int left = 0;
        int right = size - 1;

        return specialBinarySearch(ids, left, right, setPoint);
    }

    private int specialBinarySearch(int[] ids, int left, int right, int setPoint) {

        while ( right - left > 1 ) {

            int mid = right - (right - left) / 2;

            // element is in the first half
            if( setPoint != ids[mid] - mid ) {

                right = mid;
            }
            else {
                left = mid;
            }
        }
        return ids[left] + 1;
    }
}
