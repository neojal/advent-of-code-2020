package com.neojal.advent2020.day04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportValidator {

    /**
     * Part 1: The expected fields are as follows:
     *
     * byr (Birth Year)
     * iyr (Issue Year)
     * eyr (Expiration Year)
     * hgt (Height)
     * hcl (Hair Color)
     * ecl (Eye Color)
     * pid (Passport ID)
     * cid (Country ID) (optional, not required)
     *
     */
    private static final String REGEX_PART1 =
            "(?=.*byr:)(?=.*iyr:)(?=.*eyr:)(?=.*hgt:)(?=.*hcl:)(?=.*ecl:)(?=.*pid:)(?=.*cid:)?";

    /**
     * Part 2: I hate regex.
     *
     * You can continue to ignore the cid field, but each other field has strict rules about what values are valid for automatic validation:
     *
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
     * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
     * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
     * hgt (Height) - a number followed by either cm or in:
     *      If cm, the number must be at least 150 and at most 193.
     *      If in, the number must be at least 59 and at most 76.
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     * cid (Country ID) - ignored, missing or not.
     *
     */
    private static final String BIRTHDAY_YEAR_REGEX = "(?=.*byr:(19[2-9][0-9])|(200[0-2]))";
    private static final String ISSUE_YEAR_REGEX = "(?=.*iyr:(201[0-9]|2020))";
    private static final String EXPIRATION_YEAR_REGEX = "(?=.*eyr:(202[0-9]|2030))";
    private static final String HEIGHT_REGEX = "(?=.*hgt:((1([5-8][0-9]|9[0-3])cm)|((5[8-9]|6[0-9]|7[0-6])in)))";
    private static final String HAIR_COLOR_REGEX = "(?=.*hcl:#([0-9]|[a-f]){6})";
    private static final String EYE_COLOR_REGEX = "(?=.*ecl:(amb|blu|brn|gry|grn|hzl|oth))";
    private static final String PID_REGEX = "(?=.*pid:[0-9]{9})";


    /**
     *
     * @param filePath, the real line separator is a blank line. The fields of one single passport can be multiline.
     * @param regex to create a Pattern and then a Matcher to detect the valid passports.
     * @return the number of valid passports found in the input file.
     */
    private int countValidPassports(String filePath, String regex) {

        int validPassportsCount = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            while( true ) {

                line = reader.readLine();

                if (line == null || line.isBlank() ) {

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(stringBuffer.toString());

                    if( matcher.find() ) {
                        validPassportsCount ++;
                    }

                    stringBuffer.delete(0, stringBuffer.length());
                    if(line == null) {
                        break;
                    }

                } else {
                    stringBuffer.append(line + " ");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validPassportsCount;
    }

    public int countValidPassportsPart1(String filePath) {

        return countValidPassports(filePath, REGEX_PART1);
    }

    public int countValidPassportsPart2(String filePath) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(BIRTHDAY_YEAR_REGEX);
        stringBuffer.append(ISSUE_YEAR_REGEX);
        stringBuffer.append(EXPIRATION_YEAR_REGEX);
        stringBuffer.append(HEIGHT_REGEX);
        stringBuffer.append(HAIR_COLOR_REGEX);
        stringBuffer.append(EYE_COLOR_REGEX);
        stringBuffer.append(PID_REGEX);

        return countValidPassports(filePath, stringBuffer.toString());
    }
}
