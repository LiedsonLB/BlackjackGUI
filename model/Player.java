package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    private List<Card> cards = new ArrayList<>();
    private boolean clicouFicar;
    private int sumCards;

    public Player(String name){
        this.name = name;
    }

    public boolean getClicouFicar() {
        return clicouFicar;
    }

    public void setClicouFicar(boolean clicouFicar) {
        this.clicouFicar = clicouFicar;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getSumCards() {
        return sumCards;
    }

    public void setSumCards(int sumCards) {
        this.sumCards = sumCards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName(){
        return name;
    }
}
