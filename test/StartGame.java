package test;

import org.junit.jupiter.api.BeforeEach;

import controller.Game;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGame {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new Stage(), new Scene(new VBox()));
        game.toCSV();
    }
}