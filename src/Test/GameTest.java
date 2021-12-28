package Test;

import org.junit.Assert;
import org.junit.Test;
import MemoryGame.Player;

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
}
