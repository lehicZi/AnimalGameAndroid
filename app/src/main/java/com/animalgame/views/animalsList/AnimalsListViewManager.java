package com.animalgame.views.animalsList;

import com.animalgame.Comparators;
import com.animalgame.DataShared;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalsListViewManager {

    private List<Animal> animalList;
    private List<Animal> animalListToShow;

    public AnimalsListViewManager() {

        animalList = DataShared.getInstance().getAllAnimals();
        this.animalListToShow = new ArrayList<>(this.animalList);
        sortBy(AnimalsListSingleton.getInstance().getSortMod());
    }



    private void sortAlphabetically(){

        this.animalListToShow.sort(Comparators.animalComparatorName());


    }

    private void sorByUse(){

        this.animalListToShow.sort(Comparators.animalComparatorUse());

    }

    private void sortByRarete(){

        this.animalListToShow.sort(Comparators.animalComparatorRarete());

    }

    private void sortByPoids(){

        this.animalListToShow.sort(Comparators.animalComparatorPoids());

    }

    private void sortByLongueur(){

        this.animalListToShow.sort(Comparators.animalComparatorLongueur());

    }

    private void sortByLongevite(){

        this.animalListToShow.sort(Comparators.animalComparatorLongevite());

    }

    private void sortByGestation(){

        this.animalListToShow.sort(Comparators.animalComparatorGestation());

    }

    public void sortBySearch(String query){

        List<Animal> searchAnswer = new ArrayList<>();

        for (Animal animal : animalList){

            if (Utils.stringContainsString(animal.getNom(), query)){
                searchAnswer.add(animal);
            }

        }

        this.animalListToShow = searchAnswer;

    }



    public void sortBy(int sortMod){

        switch (sortMod){

            case SortMods.ALPHABETIC : {
                sortAlphabetically();
                break;
            }

            case SortMods.ISUSED: {
                sorByUse();
                break;
            }

            case SortMods.RARETE: {
                sortByRarete();
                break;
            }

            case SortMods.POIDS: {
                sortByPoids();
                break;
            }

            case SortMods.LONGUEUR: {
                sortByLongueur();
                break;
            }

            case SortMods.LONGEVITE: {
                sortByLongevite();
                break;
            }

            case SortMods.GESTATION: {
                sortByGestation();
                break;
            }


            default:{
                throw new IllegalStateException("Ce mode de tri n'existe pas !");
            }

        }


    }



    public List<Animal> getAnimalListToShow() {
        return animalListToShow;
    }


}
