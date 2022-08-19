package com.animalgame;

import com.animalgame.objects.Animal;
import com.animalgame.objects.gameModes.Game;

import java.util.List;

public class DataShared {

    /**Données partagées entre les vues
     *
     */

    private static DataShared dataShared;

    private Game game;

    private List<Animal> allAnimals;

    private DataShared() {

        AllAnimals animals = new AllAnimals();
        this.allAnimals = animals.getAllAnimals();

    }

    public static DataShared getInstance()
    {
        if (dataShared == null)
        {
            dataShared = new DataShared();
        }

        return dataShared;
    }

    public Game getGame() {
        return game;
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

    public void setNewGame(Game game){
        this.game = game;
    }


}
