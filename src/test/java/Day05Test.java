import com.neojal.advent2020.day05.BinaryBoarding;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day05Test {

    BinaryBoarding binaryBoarding = new BinaryBoarding();
    private static String filePath = "src/test/resources/day05input.txt";

    @Test
    void getHighestSeatID() throws IOException {
        assertEquals("HighestSeatId = 850, MissingSeatId = 599", binaryBoarding.getHighestSeatId(filePath));
    }
}
