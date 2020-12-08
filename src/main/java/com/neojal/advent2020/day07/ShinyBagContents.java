package com.neojal.advent2020.day07;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShinyBagContents {

    // part 2 regex.
    // 1st capture group is the outermost bag container, eg: "shiny gold bags contain...", captures "shiny gold".
    private static String PARENT_BAG_PATTERN = "(%s) bags contain.*";

    // 1st group is the number and 2nd group is the bag color. Gets the content of a bag.
    private static String CHILDREN_BAGS_PATTERN = "(\\d+) (\\w+ \\w+)";

    private String filePath;

    public ShinyBagContents(String filePath) {
        this.filePath = filePath;
    }

    public int getSumOfShinyBagContents(String rootBag, Integer number) {

        Map<String, Integer> bagsMap;

        int count = 0;

        bagsMap = getContent(rootBag);

        for (Map.Entry<String, Integer> bag : bagsMap.entrySet() ) {

            count += number * bag.getValue();
            count += getSumOfShinyBagContents( bag.getKey(), bag.getValue() * number );
        }
        return count;
    }

    private Map<String, Integer> getContent(String rootBag) {

        Map<String, Integer> bagsMap = new HashMap<>();

        try {

            BufferedReader bf = new BufferedReader( new FileReader(filePath));

            String line;
            while ( (line = bf.readLine()) != null ) {

                Pattern rootBagPattern = Pattern.compile( String.format(PARENT_BAG_PATTERN, rootBag) );
                Matcher rootBagMatcher = rootBagPattern.matcher(line);

                // found the container!
                if ( rootBagMatcher.matches() ) {

                    Pattern childrenBagsPattern = Pattern.compile( String.format(CHILDREN_BAGS_PATTERN, rootBag) );
                    Matcher childrenBagsMatcher = childrenBagsPattern.matcher(line);

                    while (childrenBagsMatcher.find()) {

                        int bagNumber = Integer.valueOf(childrenBagsMatcher.group(1));
                        String bagColor = childrenBagsMatcher.group(2);

                        bagsMap.put(bagColor, bagNumber);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bagsMap;
    }
}
