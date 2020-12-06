package com.neojal.advent2020.day06;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomsDeclaration {

    private static final String NEW_LINE = System.getProperty("line.separator");

    /**
     *
     * @param filePath
     * @return All yes answers by group: 6735, All yes answers by all people in a group: 3221.
     *
     *  From input file:
     *      Each group is separated by a blank line.
     *      Each line represents a person of a group.
     *      Each character represents a Yes Answer.
     *
     * @throws IOException
     */
    public String getSumOfYesAnswers(String filePath) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        /**
         * Part 1
         */
        int sumAnswersYes = 0;
        Set<Integer> groupAnswersYes = new HashSet<>();

        /**
         * Part 2
         */
        int sumAllAnswersYes = 0;
        Map<Integer, Integer> groupAllAnswersYes = new HashMap<>();

        int previousAnswer = 0;
        int answer;

        int persons = 0;

        while( (answer = bufferedReader.read()) != -1  ) {

            // end of a line (a person in a group)
            if( answer == NEW_LINE.charAt(0) ) {
                persons ++;

                // end of a group (a blank line has been detected)
                if ( previousAnswer == answer ) {

                    // part 1
                    sumAnswersYes += groupAnswersYes.size();
                    groupAnswersYes.clear();

                    // part 2
                    persons --;     // because of blank line
                    sumAllAnswersYes += countAllPersonsYesInGroup( persons, groupAllAnswersYes );
                    // to be ready to count the next group
                    groupAllAnswersYes.clear();
                    persons = 0;
                }
            }
            // still counting answers from the same person (line)
            else {
                // part 1
                groupAnswersYes.add(answer);

                // part 2
                if( groupAllAnswersYes.get(answer) != null )
                    groupAllAnswersYes.put(answer, groupAllAnswersYes.get(answer) + 1);
                else
                    groupAllAnswersYes.put(answer, 1);
            }
            previousAnswer = answer;
        }

        // part 1
        sumAnswersYes = sumAnswersYes + groupAnswersYes.size();

        // part 2
        ++persons;      //because end of file last group's person was not added
        sumAllAnswersYes = sumAllAnswersYes + countAllPersonsYesInGroup(persons, groupAllAnswersYes);

        return String.format("All yes answers by group: %d, All yes answer by all in a group: %d.",
                sumAnswersYes, sumAllAnswersYes);
    }

    /**
     *
     * @param persons
     * @param groupAllYes
     * @return the count of persons that answered yes to same question in a group.
     * Compares number of persons vs each element in Map.
     *
     */
    private int countAllPersonsYesInGroup(int persons, Map<Integer, Integer> groupAllYes) {
        int count = 0;
        for( Integer timesAnswered : groupAllYes.values()) {

            if ( timesAnswered == persons )
                count ++;
        }
        return count;
    }
}
