package model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
    private String number;
    private String naipe;

    public Card() {
        Random random = new Random();
        setWidth(150);
        setHeight(200);

        String numeroAleatorio = "23456789TJQKA";
        char numeroCarta = numeroAleatorio.charAt(random.nextInt(numeroAleatorio.length()));

        String naipes = "CEOP";
        char naipeAleatorio = naipes.charAt(random.nextInt(naipes.length()));

        if (numeroCarta == 'T') {
            this.number = "10";
        } else {
            this.number = String.valueOf(numeroCarta);
        }

        this.naipe = String.valueOf(naipeAleatorio);

        // draw card
        String imagePath = "./img/cards/" + number + naipe + ".jpg";
        Image backgroundImage = new Image(imagePath);

        ImagePattern imagePattern = new ImagePattern(backgroundImage, 0, 0, 1, 1, true);

        setFill(imagePattern);
    }

    public String getNumber() {
        return number;
    }

    public String getNaipe() {
        return naipe;
    }
}
