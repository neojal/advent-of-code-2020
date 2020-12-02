package com.neojal.advent2020.day02;

import java.util.ArrayList;

public class PasswordDebug {

    private class Password {
        private int digit1;
        private int digit2;
        private Character letter;
        private String password;

        private Password(String passwordLine) {
            String[] el = passwordLine.split(" ");

            String[] limits = el[0].split("-");
            this.digit1 = Integer.valueOf(limits[0]);
            this.digit2 = Integer.valueOf(limits[1]);

            this.letter = el[1].charAt(0);
            this.password = el[2];
        }
    }

    /**
     *
     * @param passwordLines
     * @return the number of valid passwords according to the policy
     *
     * 1-3 a: abcde         (valid)
     * 1-3 b: cdefg         (not valid)
     * 2-9 c: ccccccccc     (valid)
     */
    public int getValidPasswords(ArrayList<String> passwordLines) {

        int validPasswords = 0;

        for(String passwordLine : passwordLines) {
            Password password = new Password(passwordLine);

            long count = password.password.chars().mapToObj(c -> (char) c).
                    filter(c -> c == password.letter).count();

            if(count >= password.digit1 && count <= password.digit2) {
                validPasswords ++;
            }
        }
        return validPasswords;
    }

    /**
     *
     * @param passwordLines
     * @return the number of valid passwordLines according to the policy
     *
     * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
     * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
     * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
     */
    public int getValidPasswordsNewPolicy(ArrayList<String> passwordLines) {
        int validPasswords = 0;
        for(String passwordLine : passwordLines) {

            Password password = new Password(passwordLine);

            if(password.password.charAt( password.digit1-1 ) == password.letter ^
                    password.password.charAt( password.digit2-1 ) == password.letter) {
                validPasswords ++;
            }
        }
        return validPasswords;
    }
}