import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day10.JoltAdapter;
import com.neojal.advent2020.day11.SeatingSystem;
import com.neojal.advent2020.day11.SeatingSystemPart2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day11Test {

    private static  final String filePath = "src/test/resources/day11input.txt";

    @Test
    void getOccupiedSeats() throws IOException {

        SeatingSystem seatingSystem = new SeatingSystem();
        MyFileReader myFileReader = new MyFileReader(filePath);
        char[][] seatingArea = myFileReader.getTwoDimensionCharArray();

        assertEquals(2321, seatingSystem.getOccupiedSeats( seatingArea ));
    }

    @Test
    void getOccupiedSeatsPart2() throws IOException {

        SeatingSystemPart2 seatingSystemPart2 = new SeatingSystemPart2();
        MyFileReader myFileReader = new MyFileReader(filePath);
        char[][] seatingArea = myFileReader.getTwoDimensionCharArray();

        assertEquals(26, seatingSystemPart2.getOccupiedSeats( seatingArea ));
    }



}
