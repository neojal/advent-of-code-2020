import com.neojal.advent2020.day06.CustomsDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day06Test {

    CustomsDeclaration customsDeclaration = new CustomsDeclaration();
    private static String filePath = "src/test/resources/day06input.txt";

    @Test
    void testGetSumOfYesAnswers() throws IOException {
        assertEquals("All yes answers by group: 6735, All yes answer by all in a group: 3221.",
                customsDeclaration.getSumOfYesAnswers(filePath));
    }
}
