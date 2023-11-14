package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controller.Game;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;

public class Menu extends BorderPane {

    public Menu(Stage menu) {
        initializeMenu(menu);
    }

    private void initializeMenu(Stage menu) {
        menu.setTitle("Blackjack Challenge Game by Liedson LB");
        menu.setWidth(800);
        menu.setHeight(600);

        Image icon = new Image("/img/icon.png");
        menu.getIcons().add(icon);

        BorderPane screen = new BorderPane();
        screen.getStyleClass().add("background");

        Button startBtn = new Button("Começar");
        Button cpuGame = new Button("Jogar com a CPU (Em breve)");
        Button instructionBtn = new Button("Instruções");
        Button historyBtn = new Button("Partidas");

        startBtn.setPrefHeight(50);
        startBtn.setPrefWidth(250);
        cpuGame.setPrefHeight(50);
        cpuGame.setPrefWidth(250);
        instructionBtn.setPrefHeight(50);
        instructionBtn.setPrefWidth(250);
        historyBtn.setPrefWidth(250);

        startBtn.getStyleClass().add("btnStart");
        cpuGame.getStyleClass().add("btnStart");
        instructionBtn.getStyleClass().add("btnInstruction");
        historyBtn.getStyleClass().add("btnInstruction");

        VBox divBtnMenu = new VBox(15);
        divBtnMenu.getChildren().addAll(startBtn, historyBtn, instructionBtn);
        divBtnMenu.setAlignment(Pos.CENTER);

        Image menuImage = new Image("/img/icon.png");
        ImageView menuImg = new ImageView(menuImage);

        menuImg.setFitWidth(250);
        menuImg.setFitHeight(200);

        Text title = new Text("BACKJACK");
        title.getStyleClass().add("title");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.WHITE);
        dropShadow.setRadius(3);
        dropShadow.setSpread(10);
        title.setEffect(dropShadow);

        VBox titlePane = new VBox();
        titlePane.getChildren().add(title);
        titlePane.setAlignment(Pos.CENTER);

        VBox centerContent = new VBox(20);
        Text auth = new Text("Feito por Liedson LB");
        auth.getStyleClass().addAll("authFooter");
        centerContent.getChildren().addAll(titlePane, menuImg, divBtnMenu, auth);
        centerContent.setAlignment(Pos.CENTER);

        screen.setCenter(centerContent);

        Scene scene = new Scene(screen);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        menu.setScene(scene);
        
        startBtn.setOnAction(e -> {
            Game game = new Game(menu, scene);
            Scene novaCena = new Scene(game);
            novaCena.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            menu.setScene(novaCena);
        });

        instructionBtn.setOnAction(e -> {
            Instructions instructions = new Instructions(menu, scene);
            Scene novaCena = new Scene(instructions);
            novaCena.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            menu.setScene(novaCena);
        });

        historyBtn.setOnAction(e-> {
            GameHistory gameHistory = new GameHistory(menu, scene);
            Scene novaCena = new Scene(gameHistory);
            novaCena.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            menu.setScene(novaCena);
        });

        menu.show();
    }
}
