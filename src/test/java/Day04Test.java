import com.neojal.advent2020.day04.PassportValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    private static String filePath = "src/test/resources/day04input.txt";
    PassportValidator passportValidator = new PassportValidator();

    /**
     * Tests a simple regex over a string containing some fields.
     */
    @Test
    void countValidPassportsPart1() {
        assertEquals(219, passportValidator.countValidPassportsPart1(filePath));
    }

    /**
     * Tests a more complex regex over a string containing fields and specific conditions for most of them.
     */
    @Test
    void countValidPassportsPart2() {
        assertEquals(127, passportValidator.countValidPassportsPart2(filePath));
    }
}
