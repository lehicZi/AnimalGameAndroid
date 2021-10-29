package com.animalgame.objects.gameModes;

import com.animalgame.objects.Animal;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleGame extends Game{
    /** Classe fille de Game, représentant une partie dont le déroulé est proche d'une bataille : les joueurs gagnent
     * les cartes lorsqu'ils gagnent une manche, la partie s'arrête lorsqu'un joueur n'a plus de cartes.
     *  Se construit avec les mêmes paramètres que la classe Game :
     * @param numberPlayers le nombre de joueurs
     * @param numberRealPlayers le nombre de joueurs humains
     */

    public BattleGame(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList) {
        super(numberPlayers, numberRealPlayers, realPlayersList);
    }

    /**Pour tous les joueurs, vérifie si leurs deck actuel (initialDeck) est vide
     * Si c'est le cas, mélange les cartes gagnées (winnedCards) et remplace leur deck actuel par le deck ainsi consitué
     */

    private void checkAndReplaceDeck (){
        for (Player player : playersList.getallPlayers()){
            if (player.getPlayersInitialDeck().getNumberCards() == 0){
                player.getPlayersWinnedCards().shuffleDeck();
                player.replaceInitialDeck();
            }

        }
    }

    /** Pour tous les joueurs, enlève le premier animal de leur deck actuel (initialDeck)
     */

    private void removeAnimals(){
        for (int playerIndex = 0; playerIndex < numberPlayers; playerIndex++) {
            Player currentPlayer = playersList.getPlayer(playerIndex);
            currentPlayer.getPlayersInitialDeck().getListCards().remove(0);
        }
    }

    /**implémente le déroulé de la partie pour ce type de partie.
     */

    @Override
    public void playersFight(){

        playersList.orderPlayersList();
        //le perdant est défini comme le denier joueur de liste des joueurs ordonnée, car il n'a forcément pas gagné.
        Player playerLoser = playersList.getPlayer(numberPlayers-1);
        //Overture d'un scanner pour marquer une pause à la fin de chaque manche.
        Scanner scanner = new Scanner(System.in);

        // On enchaine les manches tant que le perdant de la dernière manche a encore des cartes.
        while (playerLoser.getPlayersInitialDeck().getNumberCards() + playerLoser.getPlayersWinnedCards().getNumberCards() > 0) {

            checkAndReplaceDeck();

            List<Animal> fightingAnimals = new ArrayList<>();
            StringBuilder annonce = new StringBuilder();
            // Le joueur qui commence est le premier joueur de la liste ordonnée, donc le joueur avec dont l'attribut Order
            // vaut 1.
            Player starter = playersList.getPlayer(0);

            printAnimalStats(0);

            addAnimalsAndBuildAnounce(0, fightingAnimals, annonce);

//            System.out.println(annonce);
            // Le codeEffectiveAttribute est le code permettant de connaître l'attribut effectif, il est print ici, mais
            // peut changer après
            int codeEffectiveAttribute = starter.attributeChoice();

            // On récupère l'animal du joueur qui commence
            Animal startersAnimal = starter.getPlayersInitialDeck().getListCards().get(0);

            System.out.println(starter + " chose " + startersAnimal.getAttributesName(codeEffectiveAttribute));

            // Partie changement d'attribut
            final int newCodeEffectiveAttribute = attributeSwitching(0, starter, startersAnimal);
            // if permettant de prendre en compte le nouveau code s'il ne vaut pas -1, sinon, l'ancien est gardé.
            codeEffectiveAttribute = newCodeEffectiveAttribute == -1 ? codeEffectiveAttribute : newCodeEffectiveAttribute;


            Animal animalWinner = Fight.animalFight(codeEffectiveAttribute, fightingAnimals);
//            System.out.println(animalWinner);
            Player playerWinner = animalWinner.getOwner();
//            System.out.println(playerWinner);
            playerWinner.incrementVictories();

            System.out.println("The fight was " + annonce);
            System.out.println(animalWinner.getOwner() + " wins with his " + animalWinner.getNom());

            // Tous les animaux de cette manche sont ajoutés aux winnedcards du gagnant
            playerWinner.getPlayersWinnedCards().getListCards().addAll(fightingAnimals);
            // Pour tous ces animaux, leur propriétaire est redéfinit sur le gagnant
            for (Animal animal : fightingAnimals){
                animal.setOwner(playerWinner);
            }

            // L'ordre des joueurs est redéfini en fonction du gagnant
            playersList.giveOrder(playerWinner);
            // La liste des joueurs est réordonnée
            playersList.orderPlayersList();

            // Le nouveau perdant est défini comme le denier joueur de liste des joueurs ordonnée,
            // car il n'a forcément pas gagné.
            playerLoser = playersList.getPlayer(numberPlayers-1);

//            System.out.println(orderPlayersList());

            // Le premier animal de chaque joueur est en fait l'animal ayant participé à cette manche.
            removeAnimals();
            // La prochaine manche se déroulera donc, de même façon, sur les animaux d'indice 0.

//            for (Player player : playersList){
//                System.out.println(player.getPlayersInitialDeck().getListCards());
//            }

            //Permet de bloquer le déroulé du code pour avoir une pause à la fin d'un manche
            System.out.println("Please tap any thing to pursue.");
            scanner.next();

        }

        scanner.close();
        System.out.println(playerLoser + " loses because he has no cards left.");
    }



}
