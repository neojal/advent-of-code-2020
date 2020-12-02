package com.neojal.advent2020.day01;

import java.util.*;
import java.util.stream.Collectors;

public class ExpenseReport {

    /**
     *
     * @param array
     * @param sum
     * @return the multiplication of two numbers that added are equal to sum param.
     */
    public static int getExpenseReportCode(int[] array, int sum)
    {
        int expenseReportCode = 0;
        Map<Integer, Integer> map = Arrays.stream(array).boxed().
                collect(Collectors.
                        toMap( k -> k, v -> sum-v ));

        for(Integer key : map.keySet()) {
            if (map.containsKey(map.get(key))) {
                expenseReportCode = key * map.get(key);
                break;
            }
        }
        return expenseReportCode;
    }

    /**
     *
     * @param array
     * @param sum
     * @return the multiplication of three numbers that added are equal to sum param.
     */
    public static int getExpenseReportCodeThree(int[] array, int sum) {

        Arrays.sort(array);
        int product = 0;

        /*
         Iterations: Without breaks: 20096, One break: 243, Two breaks: 135.
         */
        for(int i=0; i < array.length-2 ; i++) {
            for (int j = i + 1; j < array.length-1; j++) {

                if(array[i] + array[j] >= sum )
                    break;

                for (int k = j + 1; (k < array.length) ; k++) {

                    int addition = array[i] + array[j] + array[k];

                    if(addition > sum) {
                        break;
                    }

                    if(addition == sum) {
                        product = array[i] * array[j] * array[k];
                        return product;
                    }
                }
            }
        }
        return product;
    }
}