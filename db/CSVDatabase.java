package db;

import java.io.*;

import controller.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.GameHistory.GameResult;

public class CSVDatabase {
    private static final String CSV_FILE = "gameResult.csv";

    public static void adicionarAoHistorico(Game game) {
        try {
            File dataFolder = new File("../data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
    
            try (PrintWriter writer = new PrintWriter(new FileWriter(new File(dataFolder, CSV_FILE), true))) {
                writer.println(game.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Erro ao adicionar no arquivo CSV: " + e.getMessage());
        }
    }

    public static ObservableList<GameResult> loadGameResults() {
        ObservableList<GameResult> gameResults = FXCollections.observableArrayList();
        String caminhoArquivo = "../data/gameResult.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(caminhoArquivo)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String resultado = parts[0];
                    String vencedor = parts[1];
                    int pontuacaoJogador1 = Integer.parseInt(parts[2]);
                    int pontuacaoJogador2 = Integer.parseInt(parts[3]);

                    GameResult gameResult = new GameResult(resultado, vencedor, pontuacaoJogador1, pontuacaoJogador2);
                    gameResults.add(gameResult);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar resultados do arquivo CSV: " + e.getMessage());
        }

        return gameResults;
    }
}
