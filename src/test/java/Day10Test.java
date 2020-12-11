import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day10.JoltAdapter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day10Test {

    private static  final String filePath = "src/test/resources/day10input.txt";

    @Test
    void getJoltDifferencesProduct() {
        JoltAdapter joltAdapter = new JoltAdapter();
        MyFileReader myFileReader = new MyFileReader(filePath);

        assertEquals(2080,
                joltAdapter.getJoltDifferencesProduct( myFileReader.getIntArray() ));
    }

    @Test
    void getNumberOfAllPossibleArrangements() {
        JoltAdapter joltAdapter = new JoltAdapter();
        MyFileReader myFileReader = new MyFileReader(filePath);

        assertEquals(6.908379398144E12,
                joltAdapter.getNumberOfAllPossibleArrangements( myFileReader.getIntArray() ));
    }



}
