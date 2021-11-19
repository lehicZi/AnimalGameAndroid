package com.animalgame.views.animalsList;

import com.animalgame.objects.Animal;

public class AnimalsListSingleton {

    private static AnimalsListSingleton animalsListSingleton;

    private Animal selectedAnimal;
    private int position;


    private AnimalsListSingleton() {
        this.position = 0;
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
}
