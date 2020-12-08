import com.neojal.advent2020.day07.LuggageProcessing;
import com.neojal.advent2020.day07.ShinyBagContents;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day07Test {


    private static  final String filePath = "src/test/resources/day07input.txt";

    private static final String rootBag = "shiny gold";
    private static final Integer rootNumber = 1;


    LuggageProcessing luggageProcessing = new LuggageProcessing();

    ShinyBagContents shinyBagContents = new ShinyBagContents(filePath);

    @Test
    void getBagsContainingShinyGoldBag() throws IOException {
        assertEquals(316,
                luggageProcessing.getBagsContainingShinyGoldBags(filePath));
    }

    @Test
    void getSumOfShinyBagContents() {
        assertEquals(11310, shinyBagContents.getSumOfShinyBagContents( rootBag, rootNumber));
    }

}
