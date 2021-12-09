import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingoGameManagerTest {

    BingoGameManager gameManager = new BingoGameManager();


    @Test
    void testSampleGame() {
        gameManager.loadGame("sampleInput");
        int result = gameManager.playGame(false);
        assertEquals(result, 4512);
    }

    @Test
    void testSampleGameLastWin() {
        gameManager.loadGame("sampleInput");
        int result = gameManager.playGame(true);
        assertEquals(result, 1924);
    }
}
