package com.animalgame.objects.gameModes;

import com.animalgame.objects.Animal;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;

import java.util.List;

public class BattleGame extends Game{
    /** Classe fille de Game, représentant une partie dont le déroulé est proche d'une bataille : les joueurs gagnent
     * les cartes lorsqu'ils gagnent une manche, la partie s'arrête lorsqu'un joueur n'a plus de cartes.
     *  Se construit avec les mêmes paramètres que la classe Game :
     * @param numberPlayers le nombre de joueurs
     * @param numberRealPlayers le nombre de joueurs humains
     */


    public BattleGame(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList, PlayersList AIPlayersList, PlayersList playersList) {
        super(numberPlayers, numberRealPlayers, realPlayersList, AIPlayersList, playersList);
        this.gameMode = GameModes.BATTLEGAME;
    }


    @Override
    public boolean isFinished (){

        for (int playerIndex = 1; playerIndex < playersList.getallPlayers().size(); playerIndex++){
            Player player = playersList.getPlayer(playerIndex);

            if ( player.getPlayersInitialDeck().getNumberCards() + player.getPlayersWinnedCards().getNumberCards() == 0){

                return true;
            }

        }

        return false;

    }

    /**Pour tous les joueurs, vérifie si leurs deck actuel (initialDeck) est vide
     * Si c'est le cas, mélange les cartes gagnées (winnedCards) et remplace leur deck actuel par le deck ainsi consitué
     */

    private void checkAndReplaceDeck (){
        for (Player player : playersList.getallPlayers()){
            if (player.getPlayersInitialDeck().getNumberCards() == 0){
//                System.out.println("Done for "+player);
                player.getPlayersWinnedCards().shuffleDeck();
                player.replaceInitialDeck();
            }

        }
    }

    /** Pour tous les joueurs, enlève le premier animal de leur deck actuel (initialDeck), et
     * Donne tous les animaux du fight au gagnant.
     */

    private void removeAnimalsAndGiveToWinner(){

        Fight lastFight = getLastFight();
        Player winner = lastFight.getPlayerWinner();
        List <Animal> fightingAnimal = lastFight.getFightingAnimals();

        for (int playerIndex = 0; playerIndex < numberPlayers; playerIndex++){

            Player currentPlayer = playersList.getPlayer(playerIndex);
            Animal currentAnimal = currentPlayer.getPlayersInitialDeck().getListCards().remove(0);

            currentAnimal.setOwner(winner);
        }

        winner.getPlayersWinnedCards().getListCards().addAll(fightingAnimal);

    }
    private void resetHistory(){
        history.clear();
    }

    @Override
    public void afterFightActions(){

        removeAnimalsAndGiveToWinner();
        checkAndReplaceDeck();
        resetHistory();

    }

    @Override
    public StringBuilder setSpecificGameModeIndication(){
        StringBuilder indication = new StringBuilder();

        indication.append("Ces cartes sont donc récupérées par ").append(getLastFight().getPlayerWinner()).append(" !");
        return indication;

    }





}
