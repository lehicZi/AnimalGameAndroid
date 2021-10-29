package com.animalgame.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    /**Classe permettant de définir ce qu'est un Deck
     * listCards est la liste des cartes (Animaux) que comporte le Deck. Cette dernière est directement initialisée sur
     * une liste vide
     */

    private List<Animal> listCards = new ArrayList<>();

    /**Permet de connaître le nombre de cartes du Deck
     * @return le nombre de cartes du Deck
     */

    public int getNumberCards() {
        return listCards.size();
    }

    /**Permet d'ajouter un animal au Deck
     * @param animal l'animal à ajouter
     */

    public void addCard(Animal animal){
        listCards.add(animal);
    }

    /**Mélange le Deck
     */

    public void shuffleDeck(){
        List<Animal> shuffledDeck = new ArrayList<>(this.listCards);
        Collections.shuffle(shuffledDeck);
        this.listCards = shuffledDeck;
    }

    //Getter
    public List<Animal> getListCards() {
        return listCards;
    }
}
