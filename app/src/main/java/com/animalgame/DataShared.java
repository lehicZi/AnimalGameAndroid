package com.animalgame;

import com.animalgame.objects.gameModes.Game;

public class DataShared {

    private static DataShared dataShared;

    private Game game;

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

    public void setNewGame(Game game){
        this.game = game;
    }


    public void resetGame (){
        this.game = null;
    }
}
