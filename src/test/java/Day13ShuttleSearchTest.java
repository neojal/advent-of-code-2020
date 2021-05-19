import com.neojal.advent2020.day13.Shuttle;
import com.neojal.advent2020.day13.ShuttleSearch;
import com.neojal.advent2020.day13.ShuttleSearchImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13ShuttleSearchTest {

    private static final String INPUT_EXAMPLE = "src/test/resources/day13-input-example.txt";
    private static final String INPUT = "src/test/resources/day13-input.txt";
    private static final int EARLIEST_BUSID_X_WAITING_MINUTES_EXAMPLE = 295;
    private static final int EARLIEST_BUSID_X_WAITING_MINUTES = -1;

    private static Shuttle shuttleExample;
    private static Shuttle shuttle;

    private static ShuttleSearch shuttleSearch = new ShuttleSearchImpl();

    @BeforeAll
    static void setShuttles() throws IOException {
        File fileInput = new File(INPUT_EXAMPLE);
        Scanner input = new Scanner(fileInput);
        shuttleExample = new Shuttle(input);
        input.close();

        fileInput = new File(INPUT);
        input = new Scanner(fileInput);
        shuttle = new Shuttle(input);
        input.close();
    }

    @Test
    void earliestBusIdMultipliedByWaitingMinutesExample() {
        assertEquals(EARLIEST_BUSID_X_WAITING_MINUTES_EXAMPLE,
                shuttleSearch.getEarliestBusIdMultipliedByWaitingMinutes(shuttleExample));
    }

    @Test
    void earliestBusIdMultipliedByWaitingMinutes() {
        assertEquals(EARLIEST_BUSID_X_WAITING_MINUTES,
                shuttleSearch.getEarliestBusIdMultipliedByWaitingMinutes(shuttle));
    }
}