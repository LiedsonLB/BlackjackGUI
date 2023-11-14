package view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Instructions extends Pane {
    public Instructions(Stage menu, Scene scene) {
        setPrefSize(800, 600);
        getStyleClass().add("background");

        Button backBtn = new Button("Voltar");

        backBtn.getStyleClass().add("backBtn");
        backBtn.setTranslateX(5);
        backBtn.setTranslateY(5);

        backBtn.setOnAction(event -> {
            menu.setScene(scene);
        });

        Text instructionsText = new Text("Bem-vindo às instruções do jogo Blackjack\n\n" +
                "Regras do jogo:\n" + "\n" +
                "- Objetivo: Chegar o mais perto possível de 21 pontos sem ultrapassar.\n" + "\n" +
                "- Cartas numéricas valem seu valor nominal, cartas de figuras valem 10 pontos e o Ás vale 1 ou 11 pontos, dependendo da situação.\n" + "\n" +
                "- Você receberá duas cartas no início e poderá optar por 'pedir' (receber mais cartas) ou 'ficar' (não receber mais cartas).\n" + "\n" +
                "- Se a soma das suas cartas ultrapassar 21, você perde automaticamente.\n" + "\n" +
                "- O modo jogar contra o computador será implementado em breve\n\n" + "\n" +
                "Divirta-se jogando Blackjack e boa sorte!"
        );
        
        instructionsText.getStyleClass().add("instructionsText");
        instructionsText.setTranslateX(40);
        double offsetY = backBtn.getHeight() + 100;
        instructionsText.setLayoutY(offsetY);
        instructionsText.setWrappingWidth(700); 

        getChildren().addAll(backBtn, instructionsText);
    }
}
