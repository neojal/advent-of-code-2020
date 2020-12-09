import com.neojal.advent2020.MyFileReader;
import com.neojal.advent2020.day09.EncodingError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day09Test {

    private static  final String filePath = "src/test/resources/day09input.txt";
    private static final Integer PREAMBLE_PART1 = 25;

    // Part2: this number is the result of part1
    private static final Integer INVALID_NUMBER_FROM_PART1 = 22477624;

    @Test
    void getFirstNotSumOfTwoPreambleNumbers() {
        EncodingError encodingError = new EncodingError();
        MyFileReader myFileReader = new MyFileReader(filePath);

        assertEquals(22477624,
                encodingError.getFirstNotSumOfTwoPreambleNumbers(
                        myFileReader.getLongArray(), PREAMBLE_PART1) );
    }

    @Test
    void getEncryptionWeakness() {
        EncodingError encodingError = new EncodingError();
        MyFileReader myFileReader = new MyFileReader(filePath);

        assertEquals(2980044, encodingError.getEncryptionWeakness( myFileReader.getLongArray(), INVALID_NUMBER_FROM_PART1 ) );
    }



}
