package com.animalgame.objects.player;

import com.animalgame.Comparators;
import com.animalgame.Utils;

import java.util.ArrayList;
import java.util.List;

public class PlayersList {
    /**Classe permettant de gérer les listes de joueurs grâce à de nouvelles méthodes utilitaires
     * playerslist est une liste de Player.
     */

    private final List<Player> playersList;

    /** Constructeur de la classe
     * Initialisation de la playerlist
     */

    public PlayersList() {
        this.playersList = new ArrayList<>();
    }

    /**Équivalant de addAll() pour les List
     * @param listToAdd la PlayerList à ajouter
     */

    public void addAllPlayers(PlayersList listToAdd){
        playersList.addAll(listToAdd.getallPlayers());
    }

    /**Équivalent de add() pour les List
     * @param player le joueur à ajouter
     */

    public void addPlayer(Player player){
        this.playersList.add(player);
    }

    /** Équivalent de get() pour les List
     * @param playerIndex l'indice du joueur à retourner
     * @return le joueur à l'indice playerIndex
     */

    public Player getPlayer(int playerIndex){
        return this.playersList.get(playerIndex);
    }

    /**Récupère tous les joueurs de la PlayerList
     * @return tous les joueurs de la playersList
     */

    public List<Player> getallPlayers(){
        return this.playersList;
    }

    /**Équivalent de contains() pour les List
     * @param playerName le nom du joueur dont on veut tester l'appartenance
     * @return le joueur s'il est dans la liste, null sinon.
     */

    public Player containsPlayer(String playerName){
        for (Player player : playersList) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /** Donne l'ordre dans lequel tous les joueurs vont jouer.
     * En parcourant la liste de gauche à droite ; par exemple si dans {a,b,c,d,e} le premier joueur est c,
     * l'ordre sera {4,5,1,2,3}
     * L'ordre est directement attribué aux joueurs.
     * @param firstPlayer Le joueur qui commence.
     */
    public void giveOrder(Player firstPlayer) {
        int firstPlace = playersList.indexOf(firstPlayer);
        int place = 1;

        for (int playerIndexNext = 0; playerIndexNext < playersList.size(); playerIndexNext++){
            playersList.get((firstPlace+playerIndexNext)%playersList.size()).setOrder(place);
            place ++;
        }
    }

    /** Ordonne playersList en fonction de l'ordre de jeu des joueurs.
     */

    public void orderPlayersList(){
        playersList.sort(Comparators.playerComparator());
    }

    @Override
    public String toString(){
        String stringList = "";
        for (Player player : playersList){
            stringList += player.getPlayerName() + " ";
        }
        return stringList;
    }

}

