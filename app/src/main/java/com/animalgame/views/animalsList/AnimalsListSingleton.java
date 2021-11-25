package com.animalgame.views.animalsList;

import com.animalgame.objects.Animal;

import java.util.List;

public class AnimalsListSingleton {

    private static AnimalsListSingleton animalsListSingleton;

    private Animal selectedAnimal;
    private int position;
    private int sortMod;


    private AnimalsListSingleton() {

        this.position = 0;
        this.sortMod = SortMods.ALPHABETIC;

    }

    public static AnimalsListSingleton getInstance()
    {
        if (animalsListSingleton == null)
        {
            animalsListSingleton = new AnimalsListSingleton();
        }

        return animalsListSingleton;
    }


    public Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    public void setSelectedAnimal(Animal selectedAnimal) {
        this.selectedAnimal = selectedAnimal;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSortMod() {
        return sortMod;
    }

    public void setSortMod(int sortMod) {
        this.sortMod = sortMod;
    }
}
