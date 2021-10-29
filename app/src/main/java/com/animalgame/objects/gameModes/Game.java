package com.animalgame.objects.gameModes;

import com.animalgame.AllAnimals;
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

    public Game(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList) {
        AllAnimals cards = new AllAnimals();
        this.numberPlayers = numberPlayers;
        this.numberRealPlayers = numberRealPlayers;
        listAllCards = cards.getAllAnimals();
        numberCardsFree = listAllCards.size();
        numberCardsPerplayer = numberCardsFree / numberPlayers;
        this.playersList = new PlayersList();
        this.realPlayersList = realPlayersList;
        this.AIPlayersList = new PlayersList();
        createRealPlayersIntialDecks();
        createAIPlayersListAndInitialDecks();
        playersList.addAllPlayers(realPlayersList);
        playersList.addAllPlayers(AIPlayersList);
    }

    public void setRealPlayersList (PlayersList realPlayersList){

        this.realPlayersList = realPlayersList;

    }




    /**Permet de créer la realPlayersList
     * Pour tous les joueurs humains, leur donne le nom en paramètre et un deck :
     * un deck initial, grâce à la méthode deal().
     * Pour tous les animaux du deck initial, indique que le propriétaire est le joueur possédant le deck.
     */

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

    public void createAIPlayersListAndInitialDecks() {
        Player player;
        Deck initialDeck;
        String AIsname;
        for (int nameIndex = 0; nameIndex < (numberPlayers-numberRealPlayers); nameIndex++) {
            AIsname = "Bot " + (nameIndex + 1);
            initialDeck = deal();
            player = new AIPlayer(AIsname);
            player.setPlayersInitialDeck(initialDeck);
            for (Animal animal : initialDeck.getListCards()) {
                animal.setOwner(player);
            }
            AIPlayersList.addPlayer(player);
        }

    }

    /** Permet de distribuer des decks initiaux randomisés.
     * Retire les cartes distribuées de la lisAllCards, et diminue donc le numberFreeCards.
     * @return un Deck randomisé comportant numberCardsPerPlayer cartes.
     */
    
    public Deck deal(){
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

    /**Permet de définir quel Player commence au début de la partie en interagissant avec l'utilisateur.
     * @return Le Player qui commence.
     */

    public Player defineStarter (){
        Scanner scanner = new Scanner(System.in);
        // On demande si le joueur qui commence est humain.
        System.out.println("Who starts ? (1 : a real player, 2 : a bot)");
        String userChoice = scanner.next();

        while (!(Utils.isAnInt(userChoice))
                || !Utils.isBetweenXAndY(Integer.parseInt(userChoice), 1, 2)){
            // si l'utilisateur entre une valeur erronée (pas un int ou pas entre 1 et 4), relance le choix.
            System.out.println("is it too hard to enter 1 or 2 ?");
            userChoice = scanner.next();
        }

        int starterType = Integer.parseInt(userChoice);

        if (starterType == 1){
            // S'il n'y a qu'un seul joueur humain, il commence automatiquement
            if (numberRealPlayers == 1){
                return realPlayersList.getPlayer(0);
            }
            // Sinon, on demande son nom.
            System.out.println("So who starts ? (Player's EXACT name)");
            String starterName = scanner.next();
            Player player;
            while ((player = realPlayersList.containsPlayer(starterName)) == null){
                System.out.println("Not a valid name.");
                starterName = scanner.next();
            }
            return player;
        }
        else {
            // S'il n'est pas humain, une "IA" aléatoire commence.
            Random random = new Random();
            return AIPlayersList.getPlayer(random.nextInt(numberPlayers - numberRealPlayers));
        }

    }

    /** Définis la façon dont se déroule la partie, implémentées dans les classes filles
     */
    public abstract void playersFight ();

    /**Utilisée dans playersfight
     * Permet d'ajouter les animaux se trouvant à l'indice currentfight (manche actuelle) à la figtingAnimals.
     * Permet en parallèle de contruire l'annonce des animaux ajoutés.
     * @param currentFight indice auquel les animaux sont pris.
     * @param fightingAnimals liste des animaux à construire.
     * @param annonce annonce à construire.
     */

    protected void addAnimalsAndBuildAnounce(int currentFight, List <Animal> fightingAnimals, StringBuilder annonce){

        for (int playerIndex = 0; playerIndex < numberPlayers; playerIndex++) {
            Player currentPlayer = playersList.getPlayer(playerIndex);
            Animal currentAnimal = currentPlayer.getPlayersInitialDeck().getListCards().get(currentFight);
            fightingAnimals.add(currentAnimal);

            if (playerIndex == numberPlayers - 1) {
                annonce.append(currentPlayer).append("'s ").append(currentAnimal.getNom()).append(".");
            } else {
                annonce.append(currentPlayer).append("'s ").append(currentAnimal.getNom()).append(" VS ");
            }

        }
    }

    /**Utilisée dans playersfight
     * Print les statistiques des animaux d'indice currentfight (manche actuelle) appartenant aux joueurs humains.
     * @param currentFight indice auquel les animaux sont pris
     */

    protected void printAnimalStats(int currentFight){
        for (Player player : realPlayersList.getallPlayers()){
            System.out.println(player.getPlayersInitialDeck().getListCards().get(currentFight));
        }
    }

    /**Utilisée dans playersfight
     * Permet la mecanique de changement d'attribut au sein d'une manche
     * @param currentFight indice d ela manche actuelle
     * @param p premier joueur à jouer cette manche
     * @param a animal du premier joueur à jouer
     * @return le code de l'attribut effectif après que le changement soit fait, si aucun changement n'est fait,
     * return -1.
     */

    protected int attributeSwitching(int currentFight, Player p, Animal a){

        Player starter = p;
        Animal startersAnimal = a;
        int codeEffectiveAttribute = -1;

        //Pour tous les joueurs qui ne commencent pas
        for (int notStarters = 1; notStarters < numberPlayers; notStarters ++ ){

            Player notStarter = playersList.getPlayer(notStarters);
            Animal notStartersAnimal = notStarter.getPlayersInitialDeck().getListCards().get(currentFight);
//                System.out.println(notStarter);
            // Si la rareté de l'animal du joueur est supérieure à 0 (VERT)
            if (notStartersAnimal.getRarete() > 0){
                // Si le joueur décide de changer d'attribut
                if (playersList.getPlayer(notStarters).switchAttributeProposal()){
                    int tentativeCodeEffectiveAttribute = notStarter.attributeSwitch();
                    // Si la rareté de l'animal du joueur est suffisante, c'est-à-dire strictement supérieure à celle
                    // de l'animal du joueur qui le précède.
                    if (notStartersAnimal.getRarete() > startersAnimal.getRarete()){
                        starter = notStarter;
                        startersAnimal = notStartersAnimal;
                        codeEffectiveAttribute = tentativeCodeEffectiveAttribute;
                        System.out.println(notStarter + " changed the effective attribute and chose " + notStartersAnimal.getAttributesName(codeEffectiveAttribute));
                    }
                    else {
                        System.out.println("Sorry  " + notStarter + ", you can't change the effective attribute because your " + notStartersAnimal.getNom() +"'s rareté is " + Rarete.nameRarete(notStartersAnimal.getRarete()) + " while " + starter + "'s " + startersAnimal.getNom() +"'s rareté is " + Rarete.nameRarete(startersAnimal.getRarete()));
                    }
                }
            }

        }
        return codeEffectiveAttribute;
    }

    /**trouve le gagnant d'une liste de joueurs en cherchant celui qui a le plus de victoires.
     * Ne prends pas encore en compte les égalités.
     * @return Le Player gagnant
     */

    public Player findFinalWinner(){
        Player finalWinner = playersList.getPlayer(0);
        for (Player player : playersList.getallPlayers()) {
            if (player.getVictories() > finalWinner.getVictories()) {
                finalWinner = player;
            }
        }
        return finalWinner;
    }

    // Getters
    public int getNumberCardsPerplayer() {
        return numberCardsPerplayer;
    }

    public PlayersList getPlayersList() {
        return playersList;
    }

    public int getNumberCardsFree() {
        return numberCardsFree;
    }


}
