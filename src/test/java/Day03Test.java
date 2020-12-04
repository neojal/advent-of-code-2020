import com.neojal.advent2020.day03.TobogganTrajectory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    private static String filePath = "src/test/resources/day03input.txt";
    TobogganTrajectory tobogganTrajectory = new TobogganTrajectory();

    /**
     * Part1: How many trees on the first trajectory?
     *  First trajectory, steps of: x + 3, y + 1
     */
    @Test
    void getTreesFirstTrajectory() {
        assertEquals(211, tobogganTrajectory.getTreesFirstTrajectory(filePath, 3, 1));
    }

    /**
     * Part2: Multiply the number of trees founded in all slopes
     *
     * Right 1, down 1.
     * Right 3, down 1. (This is the slope you already checked.)
     * Right 5, down 1.
     * Right 7, down 1.
     * Right 1, down 2.
     *
     */
    @Test
    void getTreesMultipleTrajectories() {
        int[][] trajectories = {{1,1}, {3,1}, {5,1}, {7,1}, {1,2}};
        assertEquals(3_584_591_857L, tobogganTrajectory.getTreesMultipleTrajectories(filePath, trajectories));
    }

}
