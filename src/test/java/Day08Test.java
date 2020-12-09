import com.neojal.advent2020.day08.Handheld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    private static String bootCodeInputFile = "src/test/resources/day08input.txt";
    Handheld handheld;

    @BeforeEach
    void init() throws FileNotFoundException {
        handheld = new Handheld( bootCodeInputFile );
    }

    @Test
    void getValueOfAccumulator() {

        assertEquals( 1420, handheld.getValueOfAccumulator());
    }

    @Test
    void getValueOfAccumulatorFixed()  {

        assertEquals( 1245, handheld.getValueOfAccumulatorFixed());
    }

}
