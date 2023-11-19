package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import controller.Game;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

public class StartGame {

    private Game game;

    @Before
    public void setUp() {
        
        new JFXPanel();

        game = new Game(null, null);
    }

    @Test
    public void testInitialGameState() {
        // Check if the initial state of the game is as expected
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getCurrentPlayer());
        assertEquals(1, game.getRound());
        assertEquals(0, game.getSumCardsProperty().get());
        // Add more assertions based on your initial state
    }

    @Test
    public void testSwitchPlayer() {
        // Check if the switchPlayer method changes the current player and increments the round
        Player initialPlayer = game.getCurrentPlayer();
        int initialRound = game.getRound();

        game.switchPlayer();

        assertNotEquals(initialPlayer, game.getCurrentPlayer());
        assertEquals(initialRound + 1, game.getRound());
        // Add more assertions based on your switchPlayer method
    }

    @Test
    public void testDetermineWinner() {
        // Check if the determineWinner method produces the expected results for various scenarios
        // Consider testing cases where player1 wins, player2 wins, it's a tie, etc.
        // Add more assertions based on your determineWinner method
    }

    @Test
    public void testSaveResult() {
        // Check if saving a result works as expected
        // You may need to mock certain dependencies or use test-specific data for file operations
        // Add more assertions based on your saveResult method
    }

    @Test
    public void testToCSV() {
        // Check if the toCSV method produces the expected CSV format
        // Add more assertions based on your toCSV method
    }
}