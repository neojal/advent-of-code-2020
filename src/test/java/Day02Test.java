import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day02.PasswordDebug;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    private static String filePath = "src/test/resources/day02input.txt";
    private static ArrayList<String> passwordLines = new MyFileReader(filePath).getInputAsList();
    private static PasswordDebug passwordDebug = new PasswordDebug();

    /**
     * Part1: How many passwords are valid according to the policies?
     */
    @Test
    void getValidPasswords() {
        assertEquals(378,
                passwordDebug.getValidPasswords(passwordLines));
    }

    /**
     * Part2: How many passwords are valid according to the correct policies?
     */
    @Test
    void getValidPasswordsNewPolicy() {
        assertEquals(280,
                passwordDebug.getValidPasswordsNewPolicy(passwordLines));
    }

}
