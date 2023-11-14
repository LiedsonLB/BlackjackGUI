package test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import controller.Game;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoudGame {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new Stage(), new Scene(new VBox()));
    }
    @Test
    void testGameInitialization() {
        assertNotNull(game);
        assertEquals(1, game.getRound());
        assertEquals(game.getPlayer1(), game.getCurrentPlayer());
        assertEquals(0, game.getSumCardsProperty().intValue());
    }
}