import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day01.ExpenseReport;
import com.neojal.advent2020.day02.PasswordDebug;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    private static String filePath = "src/test/resources/day02input.txt";
    private static ArrayList<String> passwords = new MyFileReader(filePath).getLinesAsList();
    private static PasswordDebug passwordDebug = new PasswordDebug();

    /**
     * Part1: How many passwords are valid according to their policies?
     */
    @Test
    void getValidPasswords() {
        assertEquals(378, passwordDebug.getValidPasswords(passwords));
    }

    @Test
    void getValidPasswordsNewPolicy() {
        assertEquals(280, passwordDebug.getValidPasswordsNewPolicy(passwords));
    }

}
