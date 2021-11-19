package com.animalgame.objects.gameModes;

import android.app.Activity;

import com.animalgame.AllAnimals;
import com.animalgame.DataShared;
import com.animalgame.Utils;
import com.animalgame.constant.Rarete;
import com.animalgame.objects.Animal;
import com.animalgame.objects.Deck;
import com.animalgame.objects.player.AIPlayer;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;


import java.util.*;

public abstract class Game {
    /**
     * Classe abstraite permettant de définir ce qu'est une partie.
     * numberPlayers est le nombre de joueurs, numberRealPlayers le nombre de joueurs humains (non "IA").
     * numberCardsFree est le nombre de cartes non distribuées au début d'une partie, numberCardsPerPlayer est
     * le nombre de cartes initial de chaque joueur.
     * listAllCards est la liste de tous les animaux existants
     * playersList, realPlayersLists et AIPlayersList sont respectivement des PlayersList stockant tous les joueurs,
     * des joueurs réels et des joueurs IA.
     */

    protected final int numberPlayers;
    protected final int numberRealPlayers;
    List<Animal> listAllCards;
    protected int numberCardsFree;
    protected final int numberCardsPerplayer;
    protected PlayersList realPlayersList;
    protected final PlayersList AIPlayersList;
    protected final PlayersList playersList;
    protected final List<Fight> history;
    protected final List<AttributeSwitching> attributeHistory;
    protected GameModes gameMode;

    /**
     * Constructeur de la classe.
     * Récupère toutes les cartes en instanciant la classe AllAnimals.
     * Initialise numberCardsFree au nombre d'animaux existants
     * Calcule numberCardsPerPlayer => int, donc arrondi à l'entier inférieur si la division n'est pas juste.
     * (certains animaux seront donc non utilisés)
     * Créé les playersList, realPlayersList et AIPlayersList puis instancie les realPlayersList
     * et AIPlayersList grâce aux méthodes createRealPlayersList(realPlayersNames) et createAIPlayersList().
     * ajoute les realPlayersList et AIPlayersList à la playersList.
     *
     * @param numberPlayers     Le nombre de joueurs
     * @param numberRealPlayers Le nombre de joueurs humains
     */

    public Game(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList, PlayersList AIPlayersList, PlayersList playersList) {
        this.history = new ArrayList<>();
        this.attributeHistory = new ArrayList<>();
        this.numberPlayers = numberPlayers;
        this.numberRealPlayers = numberRealPlayers;
        CreateUsedAnimalsList();
        numberCardsFree = listAllCards.size();
        numberCardsPerplayer = numberCardsFree / numberPlayers;
        this.playersList = playersList;
        this.realPlayersList = realPlayersList;
        this.AIPlayersList = AIPlayersList;
        createRealPlayersIntialDecks();
        createAIPlayersIntialDecks();
    }

    /**Permet de créer la realPlayersList
     * Pour tous les joueurs humains, leur donne le nom en paramètre et un deck :
     * un deck initial, grâce à la méthode deal().
     * Pour tous les animaux du deck initial, indique que le propriétaire est le joueur possédant le deck.
     */


    private void CreateUsedAnimalsList(){

        listAllCards = new ArrayList<>();
        List<Animal> allAnimals = DataShared.getInstance().getAllAnimals();

        for (Animal animal : allAnimals){
            if (animal.isUsed()){
                this.listAllCards.add(animal);
            }
        }

        if (this.listAllCards.size()==0){
            throw new IllegalArgumentException("Il n'y a aucun Animal utilisé !");
        }

    }

    public void createRealPlayersIntialDecks() {
        Deck initialDeck;
        for (Player player : realPlayersList.getallPlayers()) {
            initialDeck = deal();
            player.setPlayersInitialDeck(initialDeck);
            for (Animal animal : initialDeck.getListCards()) {
                animal.setOwner(player);
            }
        }
    }

    /**Permet de créer la AIPlayersList
     * Pour tous les joueurs IA, leur donne le nom "Bot n" où n est leur indice et un deck :
     * un deck initial, grâce à la méthode deal().
     * Pour tous les animaux du deck initial, indique que le propriétaire est le joueur possédant le deck.
     */

    public void createAIPlayersIntialDecks() {
        Deck initialDeck;
        for (Player player : AIPlayersList.getallPlayers()) {
            initialDeck = deal();
            player.setPlayersInitialDeck(initialDeck);
            for (Animal animal : initialDeck.getListCards()) {
                animal.setOwner(player);
            }
        }
    }

    /** Permet de distribuer des decks initiaux randomisés.
     * Retire les cartes distribuées de la lisAllCards, et diminue donc le numberFreeCards.
     * @return un Deck randomisé comportant numberCardsPerPlayer cartes.
     */
    
    private Deck deal(){
        Random random = new Random();
        Deck deckDealt = new Deck();
        int numberCardstoDeal = numberCardsPerplayer;
        while (numberCardstoDeal > 0){
            int randomCardIndex = random.nextInt(numberCardsFree);
            deckDealt.addCard(listAllCards.remove(randomCardIndex));
            numberCardsFree -= 1;
            numberCardstoDeal -= 1;
        }
        return deckDealt;
    }


    /**Utilisée dans playersfight
     * Permet d'ajouter les animaux se trouvant à l'indice currentfight (manche actuelle) à la figtingAnimals.
     * Permet en parallèle de contruire l'annonce des animaux ajoutés.
     * @param currentFight indice auquel les animaux sont pris.
     * @param fightingAnimals liste des animaux à construire.
     */

    public void addAnimals(int currentFight, List <Animal> fightingAnimals){

        for (int playerIndex = 0; playerIndex < numberPlayers; playerIndex++) {

            Player currentPlayer = playersList.getPlayer(playerIndex);
            Animal currentAnimal = currentPlayer.getPlayersInitialDeck().getListCards().get(currentFight);
            fightingAnimals.add(currentAnimal);
        }
    }


    public abstract boolean isFinished();

    public abstract void afterFightActions();

    public abstract StringBuilder setSpecificGameModeIndication();

    public void addFightToHistory(Fight fight){

        this.history.add(fight);

    }

    public Fight getLastFight(){

        return history.get(history.size()-1);

    }

    public void addSwitchToHistory(AttributeSwitching attributeSwitching){
        this.attributeHistory.add(attributeSwitching);
    }

    public AttributeSwitching getLastSwitch(){

        return this.attributeHistory.get(this.attributeHistory.size()-1);

    }

    public AttributeSwitching getPenultimateSwitch(){

        return this.attributeHistory.get(this.attributeHistory.size()-2);

    }

    public AttributeSwitching getPlayersSwitch (Player switcher){
        for (int reverseIndex = this.attributeHistory.size()-1; reverseIndex >= 0; reverseIndex --){
            if (this.attributeHistory.get(reverseIndex).getSwitcher().equals(switcher)){
                return this.attributeHistory.get(reverseIndex);
            }

        }
        throw new IllegalStateException("Il n'y a pas de joueur ayant effectué de switch dans l'historique");
    }

    public AttributeSwitching getLastEffectiveSwitch(){

        for (int reverseIndex = this.attributeHistory.size()-1; reverseIndex >= 0; reverseIndex --){
            if (this.attributeHistory.get(reverseIndex).isTheEffective()){
                return this.attributeHistory.get(reverseIndex);
            }
        }
        throw new IllegalStateException("Il n'y a pas d'attribut effectif dans l'historique !");
    }

    /**trouve le gagnant d'une liste de joueurs en cherchant celui qui a le plus de victoires.
     * Ne prends pas encore en compte les égalités.
     * @return Le Player gagnant
     */

    public List <Player> findFinalWinner(){

        List <Player> winners = new ArrayList<>();

        Player finalWinner = playersList.getPlayer(0);

        winners.add(finalWinner);

        for (Player player : playersList.getallPlayers()) {
            if (player.getVictories() > winners.get(0).getVictories()) {
                winners.clear();
                winners.add(player);
            }
            else if (player.getVictories() == winners.get(0).getVictories()){
                if (!(player.equals(winners.get(0)))) {
                    winners.add(player);
                }
            }
        }
        return winners;
    }

    public int getCurrentFightIndex(){

        return history.size();
    }

    // Getters


    public int getNumberPlayers() {
        return numberPlayers;
    }

    public int getNumberCardsPerplayer() {
        return numberCardsPerplayer;
    }

    public PlayersList getPlayersList() {
        return playersList;
    }

    public int getNumberCardsFree() {
        return numberCardsFree;
    }

    public PlayersList getRealPlayersList() {
        return realPlayersList;
    }

    public GameModes getGameMode() {
        return gameMode;
    }

    public List<Fight> getHistory() {
        return history;
    }

    public List<AttributeSwitching> getAttributeHistory() {
        return attributeHistory;
    }
}
