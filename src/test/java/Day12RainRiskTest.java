import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day12.NavigationSystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day12RainRiskTest {

    private static final String NAVIGATION_INSTRUCTIONS_INPUT = "src/test/resources/d12-navigation-instructions.txt";
    private static final int EXPECTED_MANHATTAN_DISTANCE_FROM_ORIGIN = 1441;
    private static final int EXPECTED_MANHATTAN_DISTANCE_FROM_WAYPOINT = 0;

    @Test
    void getManhattanDistanceFromOrigin() {
        NavigationSystem ferryNavigationSystem = new NavigationSystem(NavigationSystem.TYPE_FERRY);
        MyFileReader myFileReader = new MyFileReader(NAVIGATION_INSTRUCTIONS_INPUT);
        ArrayList<String> navigationInstructions = myFileReader.getInputAsList();
        assertEquals(EXPECTED_MANHATTAN_DISTANCE_FROM_ORIGIN, ferryNavigationSystem.getManhattanDistance(navigationInstructions));
    }

    @Test
    void getManhattanDistanceFromWaypoint() {
        NavigationSystem waypointNavigationSystem = new NavigationSystem(NavigationSystem.TYPE_WAYPOINT);
        MyFileReader myFileReader = new MyFileReader(NAVIGATION_INSTRUCTIONS_INPUT);
        ArrayList<String> navigationInstructions = myFileReader.getInputAsList();
        assertEquals(EXPECTED_MANHATTAN_DISTANCE_FROM_WAYPOINT,
                waypointNavigationSystem.getManhattanDistance(navigationInstructions));
    }
}