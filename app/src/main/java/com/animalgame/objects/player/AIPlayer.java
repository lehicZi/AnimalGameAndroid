package com.animalgame.objects.player;

import java.util.Random;

public class AIPlayer extends Player{
    /** Classe fille de Player, représentant un joueur IA.
     *  Se construit avec les mêmes paramètres que la classe Player :
     * @param playerName le nom du joueur.
     */

    public AIPlayer(String playerName) {
        super(playerName);
    }

    /** Implémente le choix d'attribut pour un joueur IA.
     *  Se fait aléatoirement.
     * @return un entier représentant l'attribut choisi.
     */

    @Override
    public int attributeChoice (){
        Random random = new Random();
        return random.nextInt(4)+1;
    }

    /** Implémente la proposition de choix d'attribut pour un joueur IA.
     *  réponds toujours true.
     * @return un booléen : Oui/Non.
     */

    @Override
    public  boolean switchAttributeProposal(){
        return true;
    }

    /** Implémente le choix d'attribut pour un joueur IA.
     *  Se fait aléatoirement.
     * @return un entier représentant l'attribut choisi.
     */

    @Override
    public int attributeSwitch(){
        return attributeChoice();
    }

}

