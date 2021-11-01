package com.animalgame.objects.player;
import androidx.annotation.NonNull;

import com.animalgame.objects.Deck;

import java.util.ArrayList;

public abstract class Player {
    /** Classe abstraite permettant de définir ce qu'est un joueur.
     * playerName est le nom du joueur.
     * playersInitialDeck et playersWinnedCards sont 2 Decks représentant respectivement le deck initial (actuel) du joueur et les cartes qu'il a gagné.
     * victories est le nombre de victoires du joueur au sein d'une partie.
     * order est l'ordre de passage du joueur au sein d'une manche.
     */

    private final String playerName;
    private Deck playersInitialDeck;
    private Deck playersWinnedCards;
    private int victories = 0;
    private int order;

    /** Constructeur de la classe
     * Initialise les attributs aux paramètres donnés.
     * @param playerName le nom du joueur.
     *Initialise playersWinnedCards avec une liste vide.
     */

    public Player(String playerName) {
        this.playerName = playerName;
        this.playersInitialDeck = new Deck();
        this.playersWinnedCards = new Deck();
    }

    /**Remplace le deck actuel du joueur par le deck playersWinnedCards
     */

    public void replaceInitialDeck(){
        this.playersInitialDeck = playersWinnedCards;
    }

    /**Ajoute une victoire au joueur
     */

    public void incrementVictories() {
        this.victories ++;
    }

    /** Méthode abstraite définissant la manière avec laquelle le joueur fera son choix d'attribut.
     *  Implémentée dans les classes filles.
     * @return un entier représentant le choix d'attribut du joueur
     */

    public abstract int attributeChoice ();

    /** Méthode abstraite définissant si le joueur souhaite changer d'attribut.
     *  Implémentée dans les classes filles.
     * @return un booléen : Oui/Non
     */

    public abstract boolean switchAttributeProposal();

    /** Méthode abstraite définissant la manière avec laquelle le joueur changera l'attribut choisi.
     *  Implémentée dans les classes filles.
     * @return un entier représentant le choix d'attribut du joueur
     */

    public abstract int attributeSwitch ();

    //Getters
    public String getPlayerName() {
        return playerName;
    }

    public Deck getPlayersInitialDeck() {return playersInitialDeck;}

    public Deck getPlayersWinnedCards() {
        return playersWinnedCards;
    }

    public int getVictories() {
        return victories;
    }

    public int getOrder() {
        return order;
    }

    //Setter
    public void setOrder(int order) {
        this.order = order;
    }

    public void setPlayersInitialDeck(Deck playersInitialDeck) {
        this.playersInitialDeck = playersInitialDeck;
    }

    /** Redéfinission de toString pour renvoyer uniquement le nom du joueur.
     * @return Le nom du joueur
     */

    @NonNull
    @Override
    public String toString(){
        return playerName;
    }


    @Override
    public boolean equals(Object o){
        if (!(o instanceof String) && !(o instanceof Player) ){
            return false;
        }
        else if (o instanceof Player)
        {
            return this.getPlayerName().equals(((Player) o).getPlayerName());
        }
        else
        {
            return this.getPlayerName().equals(o);
        }
    }

}
