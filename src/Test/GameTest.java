package Test;

import org.junit.Assert;
import org.junit.Test;
import MemoryGame.Player;
import Views.OptionsView;

public class GameTest {

    @Test
    public void testNPlayerNumberIncrementCorrectly() {
        // Setup
        Player[] players = {
                new Player("player 1"),
                new Player("player 2"),
                new Player("player 3")
        };

        // Actual
        int actual = players[2].getPlayerNr();

        // Expected
        int expected = 3;

        // Assert is true
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testchosenGameboardSizeReturnCorrectNumberOfRows() {
        // setup
        OptionsView ov = new OptionsView();
        String gameboardGridSize1 = "4x6";
        String gameboardGridSize2 = "5x6";
        String gameboardGridSize3 = "3x4";

        // Actual
        int[] actuals = {
                ov.getNrOfRows(gameboardGridSize1),
                ov.getNrOfRows(gameboardGridSize2),
                ov.getNrOfRows(gameboardGridSize3)
        };

        // Expected
        int[] expecteds = { 4, 5, 3 };

        // Assert is true
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testchosenGameboardSizeReturnCorrectNumberOfColums() {
        // setup
        OptionsView ov = new OptionsView();
        String gameboardGridSize1 = "3x4";
        String gameboardGridSize2 = "4x5";
        String gameboardGridSize3 = "6x6";

        // Actual
        int[] actuals = {
                ov.getNrOfCols(gameboardGridSize1),
                ov.getNrOfCols(gameboardGridSize2),
                ov.getNrOfCols(gameboardGridSize3)
        };

        // Expected
        int[] expecteds = { 4, 5, 6 };

        // Assert is true
        Assert.assertArrayEquals(expecteds, actuals);
    }
}
