import com.neojal.advent2020.day04.PassportValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    private static String filePath = "src/test/resources/day04input.txt";
    PassportValidator passportValidator = new PassportValidator();

    @Test
    void countValidPassports() {
        assertEquals(219, passportValidator.countValidPassports(filePath));
    }
}
