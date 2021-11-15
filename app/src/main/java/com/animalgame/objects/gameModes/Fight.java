package com.animalgame.objects.gameModes;

import com.animalgame.objects.Animal;
import com.animalgame.objects.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fight {
    /**Classe utilitaire permettant de définir comment si passe un combat entre n animaux.
        Constructeur privé, car la classe n'a pas à être instanciée.
     */

    private int effectiveAttribute;
    private List<Animal> fightingAnimals;
    private Animal animalWinner;
    private Player playerWinner;


    public Fight(List<Animal> fightingAnimals, int effectiveAttribute) {
        this.fightingAnimals = new ArrayList<>();

        this.fightingAnimals.addAll(fightingAnimals);

        this.effectiveAttribute = effectiveAttribute;
    }

    /** Définit un animal gagnant en fonction animaux présents et de l'attribut choisi
     */

    public void animalFight () {

        Animal winner = this.fightingAnimals.get(0);
//        System.out.println(effectiveAttribute);
//        System.out.println(fightingAnimals);
        // Pour tous les animaux
        for (Animal animal : this.fightingAnimals){
            double animalAttribute = animal.getAttribute(this.effectiveAttribute);
            double winnerAttribute = winner.getAttribute(this.effectiveAttribute);
//            System.out.println(animalAttribute);
//            System.out.println(winnerAttribute);
            // Si l'attribut de l'animal est supérieur
            if(animalAttribute > winnerAttribute){
                winner = animal;
            }
            else if (animalAttribute == winnerAttribute){
                // si la rareté de l'animal est supérieure
                if (animal.getRarete() > winner.getRarete()){
                    winner = animal;
                }
                else if (animal.getRarete() == winner.getRarete()){
                    // Si le joueur auquel appartient l'animal joue avant
                    if(animal.getOwner().getOrder() < winner.getOwner().getOrder()){
                        winner = animal;
                    }
                }
            }

        }
        this.animalWinner = winner;
        this.playerWinner = winner.getOwner();
    }

    public int getEffectiveAttribute() {
        return effectiveAttribute;
    }

    public List<Animal> getFightingAnimals() {
        return fightingAnimals;
    }

    public Animal getAnimalWinner() {
        return animalWinner;
    }

    public Player getPlayerWinner() {
        return playerWinner;
    }

}
