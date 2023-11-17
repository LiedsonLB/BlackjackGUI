package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ListIterator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.beans.property.IntegerProperty;

import db.CSVDatabase;
import model.Card;
import model.Player;
import model.Exceptios.GameSaveResultException;

public class Game extends BorderPane {
    private Stage menu;
    private Scene scene;
    private Button backBtn;
    private IntegerProperty sumCardsProperty = new SimpleIntegerProperty(0);
    private StringProperty playerRoundProperty = new SimpleStringProperty();
    private Text mensage;
    private HBox cardBox;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Map<String, Integer> cardValues;
    private int round = 0;
    private HBox actionBtn;
    private CSVDatabase csvDatabase;

    public Game(Stage menu, Scene scene) {
        this.menu = menu;
        this.scene = scene;
        player1 = new Player("Jogador 1");
        player2 = new Player("Jogador 2");
        csvDatabase = new CSVDatabase();
        cardValues = new HashMap<>();
        currentPlayer = player1;
        initializeGameUI();
    }

    private void resetGame() {
        cardBox.getChildren().clear();
        player1.getCards().clear();
        player2.getCards().clear();
        player1.setSumCards(0);
        player2.setSumCards(0);
        sumCardsProperty.set(0);
        actionBtn.setVisible(true);
        round = 1;

        // Adiciona as cartas iniciais ao jogador atual
        Card card1 = new Card();
        Card card2 = new Card();

        if (card1.getNumber().equals(card2.getNumber()) && card1.getNaipe().equals(card2.getNaipe())) {
            Card newCard = new Card();
            card2 = newCard;
        }

        int num1 = cardValues.get(card1.getNumber());
        int num2 = cardValues.get(card2.getNumber());

        if (num1 + num2 == 22) {
            num2 = 1;
        }

        currentPlayer.addCard(card1);
        currentPlayer.addCard(card2);
        currentPlayer.setSumCards(num1 + num2);
        sumCardsProperty.set(currentPlayer.getSumCards());

        // Adiciona as cartas do jogador 1 na cardBox
        cardBox.getChildren().addAll(currentPlayer.getCards());

        // Adiciona as cartas do jogador 2 na cardBox
        cardBox.getChildren().addAll(player2.getCards());
    }

    private void initializeGameUI() {
        setPrefSize(800, 600);
        getStyleClass().add("background");

        backBtn = new Button("Sair");

        // altera em tempo real o valor da pontuação
        String resulString = new String();
        Text resul = new Text(50, 100, resulString);
        resul.getStyleClass().add("resulSum");
        sumCardsProperty.bindBidirectional(new SimpleIntegerProperty(sumCardsProperty.get()));
        resul.textProperty().bind(sumCardsProperty.asString());

        // altera em tempo real o jogador da partida
        StringBuilder convertingStringPlayerRound = new StringBuilder();
        convertingStringPlayerRound.append(currentPlayer.getName());
        String playerRoundString = "";
        Text playerRound = new Text(50, 100, playerRoundString);
        playerRound.getStyleClass().add("playerRound");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.WHITE);
        dropShadow.setRadius(3);
        dropShadow.setSpread(10);
        playerRound.setEffect(dropShadow);
        playerRoundProperty.bindBidirectional(new SimpleStringProperty("Rodada do " + currentPlayer.getName()));
        playerRound.textProperty().bind(playerRoundProperty);

        VBox textoGameInfo = new VBox(10);
        HBox pontuacaoText = new HBox(5);
        pontuacaoText.setAlignment(Pos.CENTER);
        Text textPontos = new Text(50, 100, "Pontos");
        textPontos.getStyleClass().add("resulSum");
        pontuacaoText.getChildren().addAll(resul, textPontos);
        textoGameInfo.getChildren().addAll(playerRound, pontuacaoText);
        textoGameInfo.setAlignment(Pos.CENTER);

        HBox optionsBtn = new HBox(10);
        optionsBtn.getChildren().addAll(backBtn, textoGameInfo);
        optionsBtn.setTranslateX(10);
        optionsBtn.setTranslateY(10);
        textoGameInfo.getStyleClass().add("textoGameInfo");
        textoGameInfo.setTranslateY(20);
        textoGameInfo.setTranslateX(-40);

        // alinhamento do textoGameInfo dentro do HBox
        HBox.setHgrow(textoGameInfo, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(backBtn, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(optionsBtn, javafx.scene.layout.Priority.ALWAYS);
        optionsBtn.setAlignment(Pos.CENTER);

        backBtn.getStyleClass().add("backBtn");

        backBtn.setOnAction(event -> menu.setScene(scene));

        setTop(optionsBtn);

        // Opções do Game
        Button ficarBtn = new Button("Ficar");
        Button pedirBtn = new Button("Pedir");

        ficarBtn.getStyleClass().add("btnStart");
        pedirBtn.getStyleClass().add("btnStart");

        HBox ficarBox = new HBox(10, ficarBtn);
        HBox pedirBox = new HBox(10, pedirBtn);

        actionBtn = new HBox(10);
        actionBtn.getChildren().addAll(ficarBox, pedirBox);
        actionBtn.setTranslateY(-20);
        actionBtn.setAlignment(Pos.CENTER);

        setBottom(actionBtn);

        mensage = new Text();
        cardBox = new HBox(10);

        // começando o game
        Card card1 = new Card();
        Card card2 = new Card();

        if (card1.getNumber().equals(card2.getNumber()) && card1.getNaipe().equals(card2.getNaipe())) {
            Card newCard = new Card();
            card2 = newCard;
        }

        cardValues.put("A", 11);
        cardValues.put("2", 2);
        cardValues.put("3", 3);
        cardValues.put("4", 4);
        cardValues.put("5", 5);
        cardValues.put("6", 6);
        cardValues.put("7", 7);
        cardValues.put("8", 8);
        cardValues.put("9", 9);
        cardValues.put("10", 10);
        cardValues.put("J", 10);
        cardValues.put("Q", 10);
        cardValues.put("K", 10);

        int num1 = cardValues.get(card1.getNumber());
        int num2 = cardValues.get(card2.getNumber());

        // se a soma das duas primeiras cartas for 22 quer dizer que tem 2 Ás e um deve
        // valer 1
        if (num1 + num2 == 22) {
            num2 = 1;
        }

        // somando as duas cartas e adicionando-as na mesa
        currentPlayer.addCard(card1);
        currentPlayer.addCard(card2);
        currentPlayer.setSumCards(num1 + num2);
        sumCardsProperty.set(currentPlayer.getSumCards());

        cardBox.getChildren().addAll(currentPlayer.getCards());
        cardBox.setAlignment(Pos.CENTER);
        setCenter(cardBox);

        // Defina a ação do botão "Ficar"
        ficarBtn.setOnAction(event -> {
            pedirBtn.setVisible(true);
            cardBox.getChildren().clear();
            switchPlayer(); // Alternar jogador apenas se a não passar de 21
            cardBox.getChildren().addAll(currentPlayer.getCards());

            sumCardsProperty.set(currentPlayer.getSumCards());

            cardBox.getChildren().clear();
            cardBox.getChildren().add(showResetBtn());

            // Determine o vencedor ao ultrapassar 21
            mensage.setText(determineWinner());
            mensage.getStyleClass().add("instructionsText");
        });

        // Define a ação do botão "Pedir"
        pedirBtn.setOnAction(event -> {
            Card newCard = new Card();

            // se a carta for um Ás e o valor for maior que 11 o Ás deve valer 1
            if (newCard.getNumber().equals("A") && sumCardsProperty.get() > 11) {
                cardValues.put("A", 1);
            }

            int newCardValue = cardValues.get(newCard.getNumber());

            boolean cardMatched = false;

            for (ListIterator<Card> iterator = currentPlayer.getCards().listIterator(); iterator.hasNext();) {
                Card card = iterator.next();
                if (newCardValue == cardValues.get(card.getNumber()) && newCard.getNaipe().equals(card.getNaipe())) {
                    System.out.println("Carta igual");
                    iterator.remove();
                    cardMatched = true;

                    // Subtrai o valor da carta removida da pontuação do jogador
                    currentPlayer.setSumCards(currentPlayer.getSumCards() - newCardValue);
                    break;
                }
            }

            if (cardMatched) {
                sumCardsProperty.set(sumCardsProperty.get() + newCardValue);
            } else {
                cardBox.getChildren().add(newCard);

                if (sumCardsProperty.get() < 12 && cardValues.get(newCard.getNumber()) == 1) {
                    int newNum = 11;
                    sumCardsProperty.set(currentPlayer.getSumCards() + newNum);
                } else {
                    int newNum = cardValues.get(newCard.getNumber());
                    sumCardsProperty.set(currentPlayer.getSumCards() + newNum);
                }
            }

            currentPlayer.addCard(newCard);
            currentPlayer.setSumCards(currentPlayer.getSumCards() + newCardValue);

            // Condição caso o Ás passe de 21
            if (existeAs(currentPlayer) && sumCardsProperty.get() > 11) {
                sumCardsProperty.set(sumCardsProperty.get() - 10);
                System.out.println("Existe um Ás");
            }

            if (currentPlayer.getSumCards() > 21) {
                pedirBtn.setVisible(false);
                mensage.setText(determineWinner());
            }

            // Atualiza a pontuação após a adição da nova carta
            sumCardsProperty.set(currentPlayer.getSumCards());
        });

        getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
    }

    private boolean existeAs(Player jogador) {
        for (Card carta : jogador.getCards()) {
            if (carta.getNumber().equals("A")) {
                return true;
            }
        }
        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        round++;

        // Limpa o cardBox antes de adicionar as novas cartas
        cardBox.getChildren().clear();

        if (currentPlayer.getCards().isEmpty()) {
            // Adiciona duas novas cartas para o segundo jogador na primeira rodada dele
            Card card1 = new Card();
            Card card2 = new Card();

            // Certifica de que as cartas do segundo jogador não são iguais
            while (card1.getNumber().equals(card2.getNumber()) && card1.getNaipe().equals(card2.getNaipe())) {
                card2 = new Card();
            }

            currentPlayer.addCard(card1);
            currentPlayer.addCard(card2);

            int num1 = cardValues.get(card1.getNumber());
            int num2 = cardValues.get(card2.getNumber());

            if (num1 + num2 == 22) {
                num2 = 1;
            }

            if (currentPlayer.getSumCards() > 21) {
                System.out.println("salvando apos 21");
            }

            currentPlayer.setSumCards(num1 + num2);
            sumCardsProperty.set(currentPlayer.getSumCards());

            // Adiciona as cartas do jogador 1 ao cardBox
            cardBox.getChildren().addAll(currentPlayer.getCards());

            // Adiciona as cartas do jogador 2 ao cardBox
            cardBox.getChildren().addAll(player2.getCards());
        }
    }

    private String determineWinner() {
        if (player1.getSumCards() > 21 && player2.getSumCards() > 21) {
            return "Empate! Ambos os jogadores perderam! Suas pontuações ultrapassaram 21.";
        } else if (player1.getSumCards() > 21) {
            return player2.getName() + " venceu com " + player2.getSumCards() + " pontos!";
        } else if (player2.getSumCards() > 21) {
            return player1.getName() + " venceu com " + player1.getSumCards() + " pontos!";
        } else if (player1.getSumCards() < player2.getSumCards()) {
            return player2.getName() + " venceu com " + player2.getSumCards() + " pontos!";
        } else if (player1.getSumCards() > player2.getSumCards()) {
            return player1.getName() + " venceu com " + player1.getSumCards() + " pontos!";
        } else {
            return "Empate! Ambos os jogadores têm " + currentPlayer.getSumCards() + " pontos!";
        }
    }

    private VBox showResetBtn() {
        salvarResultado();
        System.out.println(toCSV());

        Button jogarNovamenteBtn = new Button("Jogar Novamente");
        jogarNovamenteBtn.getStyleClass().add("btnStart");

        jogarNovamenteBtn.setOnAction(e -> {
            resetGame();
            actionBtn.setVisible(true);
        });

        VBox resetMensagem = new VBox(10);
        resetMensagem.getChildren().addAll(mensage, jogarNovamenteBtn);
        resetMensagem.setAlignment(Pos.CENTER);

        actionBtn.setVisible(false);

        return resetMensagem;
    }

    public void salvarResultado() throws GameSaveResultException {
        String caminhoPastaData = "data";
        String nomeArquivo = "gameResult.csv";

        try {
            File pastaData = new File(caminhoPastaData);
            if (!pastaData.exists()) {
                pastaData.mkdirs();
            }

            File arquivo = new File(pastaData, nomeArquivo);
            String caminhoArquivo = arquivo.getAbsolutePath();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
                String linha = toCSV() + System.lineSeparator();
                writer.append(linha);
                System.out.println("Resultado salvo com sucesso em: " + caminhoArquivo);
            } catch (IOException e) {
                throw new GameSaveResultException("Erro ao salvar resultado.", e);
            }
        } catch (Exception e) {
            throw new GameSaveResultException("Erro ao criar diretórios.", e);
        }
    }

    public String toCSV() {
        StringBuilder csv = new StringBuilder();

        // resultado
        String resultado = determineWinner();
        csv.append(resultado).append(",");

        // nome do ganhador ou se deu empate
        String vencedor = (resultado.contains("Empate")) ? "Empate"
                : (resultado.contains(player1.getName())) ? player1.getName() : player2.getName();
        csv.append(vencedor).append(",");

        // pontuação de cada jogador
        csv.append(player1.getSumCards()).append(",");
        csv.append(player2.getSumCards()).append(",");

        return csv.toString();
    }

    public String getGameResultForTesting() {
        return determineWinner();
    }

    public IntegerProperty getSumCardsProperty() {
        return getSumCardsProperty();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getRound() {
        return round;
    }
}