package com.neojal.advent2020.day10;

import java.util.Arrays;

public class JoltAdapter
{
    /**
     *
     * @param adapterArray
     * @return the product of differencesOfOne by differencesOfThree, considering the plug (0)
     *          and a built-in adapter (+3)
     */
    public int getJoltDifferencesProduct(int[] adapterArray) {

        int differencesOfOne = 0;
        int differencesOfThree = 0;

        Arrays.sort( adapterArray );
        int previousAdapter = 0;

        for (int i = 0; i < adapterArray.length; i++) {

            // difference of 1
            if ( adapterArray[i] - 1 == previousAdapter ) {

                differencesOfOne ++;
            } else {

                differencesOfThree ++;
            }
            previousAdapter = adapterArray[i];
        }

        // because of the device built-in adapter
        differencesOfThree ++;

        return differencesOfOne * differencesOfThree;
    }

    /**
     *
     * @param adapterArray
     * @return all the possible combinations considering the rules.
     *      Series of values are al separated by 3.
     */
    public double getNumberOfAllPossibleArrangements(int[] adapterArray) {
        int differencesOfOne = 0;
        double distinctWays = 1;

        Arrays.sort( adapterArray );
        double previousAdapter = 0;

        for (int i = 0; i <= adapterArray.length; i++) {

            double adapter = 0;
            // the last adapter (+3) must be added
            if (i == adapterArray.length)
                adapter = previousAdapter + 3;
            else
                adapter = adapterArray[i];

            // difference of 1
            if ( adapter - 1 == previousAdapter) {

                differencesOfOne ++;
            }
            // A sequence of consecutive numbers has finished, lets see the possible combinations of the n elements.
            else {
                // the value of the possible combinations in the current sequence. Eg: ...5,6,7,8,9...
                double combinations;

                // this means we have 3 or more adapters in or sequence, so the combination value is bigger than 1.
                if ( differencesOfOne >= 2) {

                    // n is the number of adapters in the current sequence: n = 5
                    double n = differencesOfOne + 1;
                    // free are the number of adapters that can be avoided. Eg: 6,7,8 can be avoided. free = 3
                    double free = n - 2;

                    // this is a condition for a constant of the formula. So constant = 3 - 2 = 1
                    double constant = 0;
                    if ( free > 2 )
                        constant = free - 2;

                    // and the possible combinations of the sequence is: f(n) = 2^(free) - constant = 2^3 - 1 = 7
                    combinations = Math.pow( 2, free ) - constant;

                    // distinctWays holds the final result by multiplying all combinations.
                    distinctWays *= combinations;
                }
                // lets read another number sequence
                differencesOfOne = 0;
            }
            previousAdapter = adapter;
        }
        return distinctWays;
    }
}
