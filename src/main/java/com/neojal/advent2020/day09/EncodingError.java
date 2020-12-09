package com.neojal.advent2020.day09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EncodingError {
    public long getFirstNotSumOfTwoPreambleNumbers(long[] allNumbers, int preamble) {

        long result = 0;

        int countNumbers = preamble + 1;

        long[] evalNumbers = new long[countNumbers];

        for (int i = 0; i <= allNumbers.length - countNumbers; i++) {

            //System.out.println("---");
            int evalNumbersIndex = 0;
            for (int j = i; j < i + countNumbers; j++) {

                evalNumbers[evalNumbersIndex++] = allNumbers[j];
                // System.out.println(allNumbers[j]);
            }

            result = getNumberThatIsNotSum( evalNumbers );

            if (result != 0)
                break;
        }

        return result;
    }

    /**
     *
     * @param evalNumbers
     * @return the first number that is not a sum of any two of the numbers in evalNumbers array.
     */
    private long getNumberThatIsNotSum(long[] evalNumbers) {

        // the number that must be the sum of two others
        long sum = evalNumbers[ evalNumbers.length - 1 ];

        Map<Long, Long> map = new HashMap<>();

        for (long n : evalNumbers) {
            map.put(n, sum - n);
        }

        Boolean isSum = false;
        for ( long n : map.values() ) {

            if ( map.containsKey( n ) ) {

                isSum = true;
            }
        }

        return (!isSum) ? sum : 0;
    }


    /**
     *
     * @param longArray
     * @param invalidNumberFromPart1
     * @return the sum of two numbers (the smallest and the largest) of a sub-range of numbers that added
     *          sum the invalidNumberPart2
     */
    public long getEncryptionWeakness(long[] longArray, Integer invalidNumberFromPart1) {

        // gets the index of the invalid number from step 1.
        int zeroIndex = getInvalidNumberIndex(longArray, invalidNumberFromPart1);

        // starts from where the invalid is located and goes backwards
        for (int highIndex = zeroIndex - 1; highIndex > 1; highIndex--) {

            // now we have a range to valuate, from lowIndex to highIndex
            for (int lowIndex = highIndex - 1; lowIndex > 0; lowIndex--) {

                // The sum of all elements in range must be equal to invalidNumberFromPart1
                long sum = 0;
                // The sum of this two values of the range is the result we are looking for.
                long smallest = longArray[highIndex];
                long bigest = longArray[highIndex];
                // evaluates the sub-range
                for (int k = highIndex;
                     (k > lowIndex) && (longArray[k] < invalidNumberFromPart1) && sum < invalidNumberFromPart1;
                     k--) {

                    long currentNumber = longArray[k];
                    sum += currentNumber;

                    smallest = ( smallest < currentNumber ) ? smallest : currentNumber;
                    bigest = ( bigest > currentNumber ) ? bigest : currentNumber;

                    if( sum == invalidNumberFromPart1 ) {

                        return smallest + bigest;
                    }
                }
            }
        }
        return 0;
    }

    private int getInvalidNumberIndex(long[] longArray, long invalidNumberFromPart1) {
        int index = 0;
        for (int i = 0; i < longArray.length; i++) {
            if( longArray[i] == invalidNumberFromPart1 ) {
                index = i;
                break;
            }
        }
        return index;
    }
}
