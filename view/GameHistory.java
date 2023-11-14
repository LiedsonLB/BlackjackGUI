package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import db.CSVDatabase;
import javafx.scene.Scene;

public class GameHistory extends Pane {
    private TableView<GameResult> tableView;

    public GameHistory(Stage menu, Scene scene) {
        setPrefSize(800, 600);
        getStyleClass().add("background");

        Button backBtn = new Button("Voltar");

        backBtn.getStyleClass().add("backBtn");
        backBtn.setTranslateX(5);
        backBtn.setTranslateY(5);

        backBtn.setOnAction(event -> {
            menu.setScene(scene);
        });

        // Criação da tabela
        tableView = new TableView<>();
        tableView.setTranslateX(40);
        double offsetY = backBtn.getHeight() + 100;
        tableView.setLayoutY(offsetY);
        tableView.setPrefSize(700, 400);

        // colunas
        TableColumn<GameResult, String> resultadoColumn = new TableColumn<>("Resultado");
        resultadoColumn.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        resultadoColumn.setPrefWidth(160);

        TableColumn<GameResult, String> vencedorColumn = new TableColumn<>("Vencedor");
        vencedorColumn.setCellValueFactory(new PropertyValueFactory<>("vencedor"));
        vencedorColumn.setPrefWidth(150);

        TableColumn<GameResult, Integer> pontuacaoJogador1Column = new TableColumn<>("Pontuação Jogador 1");
        pontuacaoJogador1Column.setCellValueFactory(new PropertyValueFactory<>("pontuacaoJogador1"));
        pontuacaoJogador1Column.setPrefWidth(185);

        TableColumn<GameResult, Integer> pontuacaoJogador2Column = new TableColumn<>("Pontuação Jogador 2");
        pontuacaoJogador2Column.setCellValueFactory(new PropertyValueFactory<>("pontuacaoJogador2"));
        pontuacaoJogador2Column.setPrefWidth(185);

        // Adiciona as colunas
        tableView.getColumns().addAll(resultadoColumn, vencedorColumn, pontuacaoJogador1Column, pontuacaoJogador2Column);
        tableView.getStyleClass().add("table");
        tableView.setCenterShape(true);

        getChildren().addAll(backBtn, tableView);

        // Atualiza a tabela na cena
        refreshTable();
    }

    // Método para atualizar a tabela com os dados do arquivo CSV
    private void refreshTable() {
        ObservableList<GameResult> gameResults = FXCollections.observableArrayList();
        // Carrega os dados do arquivo CSV e adiciona à lista
        loadGameResults(gameResults);
        // carrega os dados na tabela
        tableView.setItems(gameResults);
    }

    // carrega o arquivo CSV
    private void loadGameResults(ObservableList<GameResult> gameResults) {

        gameResults.addAll(CSVDatabase.loadGameResults());
    }

    // Classe de resultado do game
    public static class GameResult {
        private final String resultado;
        private final String vencedor;
        private final int pontuacaoJogador1;
        private final int pontuacaoJogador2;

        public GameResult(String resultado, String vencedor, int pontuacaoJogador1, int pontuacaoJogador2) {
            this.resultado = resultado;
            this.vencedor = vencedor;
            this.pontuacaoJogador1 = pontuacaoJogador1;
            this.pontuacaoJogador2 = pontuacaoJogador2;
        }

        public String getResultado() {
            return resultado;
        }

        public String getVencedor() {
            return vencedor;
        }

        public int getPontuacaoJogador1() {
            return pontuacaoJogador1;
        }

        public int getPontuacaoJogador2() {
            return pontuacaoJogador2;
        }
    }
}
