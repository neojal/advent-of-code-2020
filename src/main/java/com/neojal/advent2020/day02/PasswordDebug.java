package com.neojal.advent2020.day02;

import java.util.ArrayList;

public class PasswordDebug {

    private int digit1;
    private int digit2;
    private Character letter;
    private String password;

    private PasswordDebug(String passwordLine) {
        String[] el = passwordLine.split(" ");

        String[] limits = el[0].split("-");
        this.digit1 = Integer.valueOf(limits[0]);
        this.digit2 = Integer.valueOf(limits[1]);

        this.letter = el[1].charAt(0);
        this.password = el[2];
    }

    public PasswordDebug() {}

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
            PasswordDebug debug = new PasswordDebug(passwordLine);

            long count = debug.password.chars().mapToObj(c -> (char) c).
                    filter(c -> c == debug.letter).count();

            if(count >= debug.digit1 && count <= debug.digit2) {
                validPasswords ++;
            }
        }
        return validPasswords;
    }

    /**
     *
     * @param passwords
     * @return the number of valid passwords according to the policy
     *
     * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
     * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
     * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
     */
    public int getValidPasswordsNewPolicy(ArrayList<String> passwords) {
        int validPasswords = 0;
        for(String password : passwords) {

            PasswordDebug debug = new PasswordDebug(password);

            if(debug.password.charAt( debug.digit1-1 ) == debug.letter ^
                    debug.password.charAt( debug.digit2-1 ) == debug.letter) {
                validPasswords ++;
            }
        }
        return validPasswords;
    }
}