package com.animalgame;

import com.animalgame.objects.Animal;
import com.animalgame.objects.player.Player;

import java.util.Comparator;

public class Comparators {

    /**Comparateur utilisé pour ordonner la playersList
     */

    public static Comparator<Player> playerComparator(){
        return Comparator.comparingInt(Player::getOrder);
    }

    /**Comparateurs utilisés pour ordonner les annimaux
     */

    public static Comparator<Animal> animalComparatorName(){
        return Comparator.comparing(Animal::getNom);
    }

    public static Comparator<Animal> animalComparatorUse(){
        return (animal1, animal2) -> {
            if (animal1.isUsed() == animal2.isUsed()){
                return 0;
            }
            else if (!animal1.isUsed()) {
                return 1;
            }
            else {
                return -1;
            }
        };
    }

    public static Comparator<Animal> animalComparatorRarete(){
        return (animal1, animal2) -> {
            if (animal1.getRarete() == animal2.getRarete()){
                return 0;
            }
            else if (animal1.getRarete() < animal2.getRarete()){
                return 1;
            }
            else {
                return -1;
            }
        };
    }

    public static Comparator<Animal> animalComparatorPoids(){
        return (animal1, animal2) -> {
            if (animal1.getPoids() == animal2.getPoids()){
                return 0;
            }
            else if (animal1.getPoids() < animal2.getPoids()){
                return 1;
            }
            else {
                return -1;
            }
        };
    }

    public static Comparator<Animal> animalComparatorLongueur(){
        return (animal1, animal2) -> {
            if (animal1.getLongueur() == animal2.getLongueur()){
                return 0;
            }
            else if (animal1.getLongueur() < animal2.getLongueur()){
                return 1;
            }
            else {
                return -1;
            }
        };
    }

    public static Comparator<Animal> animalComparatorLongevite(){
        return (animal1, animal2) -> {
            if (animal1.getLongevite() == animal2.getLongevite()){
                return 0;
            }
            else if (animal1.getLongevite() < animal2.getLongevite()){
                return 1;
            }
            else {
                return -1;
            }
        };
    }

    public static Comparator<Animal> animalComparatorGestation(){
        return (animal1, animal2) -> {
            if (animal1.getGestationIncubation() == animal2.getGestationIncubation()){
                return 0;
            }
            else if (animal1.getGestationIncubation() < animal2.getGestationIncubation()){
                return 1;
            }
            else {
                return -1;
            }
        };
    }





}
