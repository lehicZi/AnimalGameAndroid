package com.animalgame;

import com.animalgame.objects.Animal;
import com.animalgame.objects.gameModes.Game;

import java.util.List;

public class DataShared {

    private static DataShared dataShared;

    private Game game;

    private List<Animal> allAnimals;

    private DataShared() {

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

    public void setAllAnimals(List<Animal> allAnimals) {
        this.allAnimals = allAnimals;
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

    public void setNewGame(Game game){
        this.game = game;
    }


    public void resetGame (){
        this.game = null;
    }
}
