package com.animalgame.objects.gameModes;

import com.animalgame.objects.player.PlayersList;

public class OneDeckGame extends Game{
    /** Classe fille de Game, représentant une partie où un seul parcours des decks des joueurs est fait.
     *  Se construit avec les mêmes paramètres que la classe Game :
     * @param numberPlayers le nombre de joueurs
     * @param numberRealPlayers le nombre de joueurs humains
     */

    public OneDeckGame(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList, PlayersList AIPlayersList, PlayersList playersList) {
        super(numberPlayers, numberRealPlayers, realPlayersList, AIPlayersList, playersList);
        this.gameMode = GameModes.ONEDECKGAME;
    }

    /**implémente le déroulé de la partie pour ce type de partie.
     */

    @Override
    public boolean isFinished (){

        return history.size() >= numberCardsPerplayer;

    }

    @Override
    public void afterFightActions(){}

    @Override
    public StringBuilder setSpecificGameModeIndication(){

        StringBuilder indication = new StringBuilder();
        return indication;

    }

    @Override
    public String toString(){
        return "mode rapide";
    }



}
