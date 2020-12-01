import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day01.ExpenseReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    private static String filePath = "src/test/resources/inputDay01.txt";
    private static int[] array = MyFileReader.getIntArray(filePath);

    /**
     * Part1: expects the product of two numbers that added are equal to 2020
     */
    @Test
    void getExpenseReportCode() {
        assertEquals(713184, ExpenseReport.getExpenseReportCode(array, 2020));
    }

    /**
     * Part2: expects the product of three numbers that added are equal to 2020.
     */
    @Test
    void getExpenseRecordThree() {
        assertEquals(261244452, ExpenseReport.getExpenseReportCodeThree(array, 2020));
    }
}
