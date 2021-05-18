import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day12.NavigationSystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12NavigationSystemTest {

    private static final String NAVIGATION_INSTRUCTIONS_INPUT_EXAMPLE = "src/test/resources/d12-example-input.txt";
    private static final String NAVIGATION_INSTRUCTIONS_INPUT = "src/test/resources/d12-input.txt";
    private static final int EXPECTED_MANHATTAN_DISTANCE_FERRY_EXAMPLE = 25;
    private static final int EXPECTED_MANHATTAN_DISTANCE_FERRY = 1441;
    private static final int EXPECTED_MANHATTAN_DISTANCE_WAYPOINT_EXAMPLE = 286;
    private static final int EXPECTED_MANHATTAN_DISTANCE_WAYPOINT = 61616;

    private MyFileReader exampleInputFileReader = new MyFileReader(NAVIGATION_INSTRUCTIONS_INPUT_EXAMPLE);
    private MyFileReader inputFileReader = new MyFileReader(NAVIGATION_INSTRUCTIONS_INPUT);

    private NavigationSystem ferryNavigationSystem = new NavigationSystem(NavigationSystem.TYPE_FERRY);
    private NavigationSystem waypointNavigationSystem = new NavigationSystem(NavigationSystem.TYPE_WAYPOINT);

    private ArrayList<String> exampleInstructions = exampleInputFileReader.getInputAsList();
    private ArrayList<String> instructions = inputFileReader.getInputAsList();

    @Test
    void getManhattanDistanceFerryExample() {
        assertEquals(EXPECTED_MANHATTAN_DISTANCE_FERRY_EXAMPLE,
                ferryNavigationSystem.getManhattanDistance(exampleInstructions));
    }
    @Test
    void getManhattanDistanceFerry() {
        assertEquals(EXPECTED_MANHATTAN_DISTANCE_FERRY,
                ferryNavigationSystem.getManhattanDistance(instructions));
    }

    @Test
    void getManhattanDistanceWaypointExample() {
        assertEquals(EXPECTED_MANHATTAN_DISTANCE_WAYPOINT_EXAMPLE,
                waypointNavigationSystem.getManhattanDistance(exampleInstructions));
    }

    @Test
    void getManhattanDistanceWaypoint() {
        assertEquals(EXPECTED_MANHATTAN_DISTANCE_WAYPOINT,
                waypointNavigationSystem.getManhattanDistance(instructions));
    }
}