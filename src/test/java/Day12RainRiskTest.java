import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day12.FerryNavigationSystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day12RainRiskTest {

    private static final String NAVIGATION_INSTRUCTIONS_INPUT = "src/test/resources/d12-navigation-instructions.txt";
    private static final int EXPECTED_MANHATTAN_DISTANCE = 1441;

    @Test
    void getOccupiedSeats() {

        FerryNavigationSystem ferryNavigationSystem = FerryNavigationSystem.getInstance();
        MyFileReader myFileReader = new MyFileReader(NAVIGATION_INSTRUCTIONS_INPUT);
        ArrayList<String> navigationInstructions = myFileReader.getInputAsList();

        assertEquals(EXPECTED_MANHATTAN_DISTANCE, ferryNavigationSystem.getManhattanDistance(navigationInstructions));
    }
}