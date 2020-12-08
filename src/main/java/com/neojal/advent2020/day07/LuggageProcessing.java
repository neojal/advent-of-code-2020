package com.neojal.advent2020.day07;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LuggageProcessing {

    private final static String SHINY_GOLD = "shiny gold";

    // Part 1: parameter %s is the bag contained, shiny gold
    private static String CONTAINER_BAG_PATTERN = "(\\w+ \\w+).*%s.*";

    Set<Integer> resultSet = new HashSet<>();

    /**
     *
     * @param filePath
     * @return How many bag colors can eventually contain at least one shiny gold bag?
     */
    public int getBagsContainingShinyGoldBags(String filePath) throws IOException {



        Set<String> bagsSet = new HashSet<>();


        bagsSet.add(SHINY_GOLD);

        while ( bagsSet.size() != 0 ) {

            bagsSet = getContainers(bagsSet, filePath);
        }

        return resultSet.size();
    }

    Set<String> getContainers(Set<String> bagsSet, String filePath) throws IOException {

        Set<String> tmpBagSet = new HashSet<>();

        for ( String bag : bagsSet ) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;



            int lineNumber = 0;
            while ( (line = bufferedReader.readLine()) != null) {

                lineNumber ++;

                Pattern pattern = Pattern.compile( String.format(CONTAINER_BAG_PATTERN, bag) );
                Matcher matcher = pattern.matcher(line);

                if ( matcher.matches() ) {

                    String containerBag = matcher.group(1).trim();

                    tmpBagSet.add(containerBag);
                    resultSet.add(lineNumber);
                }
            }
            bufferedReader.close();
        }

        return tmpBagSet;
    }

}
