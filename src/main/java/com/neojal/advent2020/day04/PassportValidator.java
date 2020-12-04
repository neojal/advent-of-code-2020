package com.neojal.advent2020.day04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PassportValidator {

    private static final String[] REQUIRED_FIELDS_PART1 =
            {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public int countValidPassports(String filePath) {

        int validPassportsCount = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";



            while( true ) {

                line = reader.readLine();
                if (line == null || line.isBlank() ) {
                    Boolean isValidPassport = true;

                    for(String field : REQUIRED_FIELDS_PART1) {

                        if(!stringBuffer.toString().contains(field + ":")) {
                            isValidPassport = false;
                            break;
                        }
                    }

                    if(isValidPassport) {
                        validPassportsCount ++;
                    }

                    stringBuffer.delete(0, stringBuffer.length());
                    if(line == null)
                        break;
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
}
