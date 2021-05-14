import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day12.FerryNavigationSystem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day12RainRiskTest {

    private static final String NAVIGATION_INSTRUCTIONS_INPUT = "src/test/resources/d12-navigation-instructions.txt";

    @Test
    void getOccupiedSeats() throws IOException {

        FerryNavigationSystem ferryNavigationSystem = FerryNavigationSystem.getInstance();
        MyFileReader myFileReader = new MyFileReader(NAVIGATION_INSTRUCTIONS_INPUT);
        ArrayList<String> navigationInstructions = myFileReader.getInputAsList();

        assertEquals(0, ferryNavigationSystem.getManhattanDistance(navigationInstructions));
    }
}