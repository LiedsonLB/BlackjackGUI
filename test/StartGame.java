package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import controller.Game;
import javafx.embed.swing.JFXPanel;
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

        assertNotNull(game.getPlayer1());
        assertNotNull(game.getCurrentPlayer());
        assertEquals(1, game.getRound());
        assertEquals(0, game.getSumCardsProperty().get());

    }

    @Test
    public void testSwitchPlayer() {

        Player initialPlayer = game.getCurrentPlayer();
        int initialRound = game.getRound();

        game.switchPlayer();

        assertNotEquals(initialPlayer, game.getCurrentPlayer());
        assertEquals(initialRound + 1, game.getRound());

    }

    @Test
    public void testDetermineWinner() {

    }

    @Test
    public void testSaveResult() {

    }

    @Test
    public void testToCSV() {

    }
}