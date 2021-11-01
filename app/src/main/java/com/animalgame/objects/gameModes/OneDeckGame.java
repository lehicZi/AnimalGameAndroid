package com.animalgame.objects.gameModes;

import com.animalgame.objects.Animal;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OneDeckGame extends Game{
    /** Classe fille de Game, représentant une partie où un seul parcours des decks des joueurs est fait.
     *  Se construit avec les mêmes paramètres que la classe Game :
     * @param numberPlayers le nombre de joueurs
     * @param numberRealPlayers le nombre de joueurs humains
     */

    public OneDeckGame(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList, PlayersList AIPlayersList, PlayersList playersList) {
        super(numberPlayers, numberRealPlayers, realPlayersList, AIPlayersList, playersList);
    }

    /**implémente le déroulé de la partie pour ce type de partie.
     */

    @Override
    public void playersFight(){
        //Overture d'un scanner pour marquer une pause à la fin de chaque manche.
        Scanner scanner = new Scanner(System.in);
        // On parcourt les cartes, currentFight représente une manche.
        for (int currentFight = 0; currentFight < numberCardsPerplayer; currentFight++) {
            playersList.orderPlayersList();
            List<Animal> fightingAnimals = new ArrayList<>();
            StringBuilder annonce = new StringBuilder();
            // Le joueur qui commence est le premier joueur de la liste ordonnée, donc le joueur avec dont l'attribut Order
            // vaut 1.
            Player starter = playersList.getPlayer(0);

            addAnimalsAndBuildAnounce(currentFight, fightingAnimals, annonce);

//            System.out.println(annonce);

            printAnimalStats(currentFight);

            // Le codeEffectiveAttribute est le code permettant de connaître l'attribut effectif, il est print ici, mais
            // peut changer après
            int codeEffectiveAttribute = starter.attributeChoice();

            // On récupère l'animal du joueur qui commence
            Animal startersAnimal = starter.getPlayersInitialDeck().getListCards().get(currentFight);

            System.out.println(starter + " chose " + startersAnimal.getAttributesName(codeEffectiveAttribute));

            // Partie changement d'attribut
            final int newCodeEffectiveAttribute = attributeSwitching(currentFight, starter, startersAnimal);
            // if permettant de prendre en compte le nouveau code s'il ne vaut pas -1, sinon, l'ancien est gardé.
            codeEffectiveAttribute = newCodeEffectiveAttribute == -1 ? codeEffectiveAttribute : newCodeEffectiveAttribute;


            Animal animalWinner = Fight.animalFight(codeEffectiveAttribute, fightingAnimals);
//            System.out.println(animalWinner);
            Player playerWinner = animalWinner.getOwner();
//            System.out.println(playerWinner);

            // L'ordre des joueurs est redéfini en fonction du gagnant
            playersList.giveOrder(playerWinner);

//            System.out.println(orderPlayersList());

            System.out.println("The fight was " + annonce);
            System.out.println(animalWinner.getOwner() + " wins with his " + animalWinner.getNom());

            playerWinner.incrementVictories();

            //Permet de bloquer le déroulé du code pour avoir une pause à la fin d'un manche
            System.out.println("Please tap any thing to pursue.");
            scanner.next();
        }
        scanner.close();
    }




}
