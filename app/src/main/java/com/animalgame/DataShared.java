package com.animalgame;

import com.animalgame.objects.gameModes.BattleGame;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.objects.gameModes.OneDeckGame;
import com.animalgame.objects.player.PlayersList;

public class DataShared {

    private static DataShared dataShared;

    private Game game;

    private DataShared() {
;
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

    public void setNewOneDeckGame(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList, PlayersList AIPlayersList, PlayersList playersList){
        this.game = new OneDeckGame(numberPlayers,numberRealPlayers, realPlayersList, AIPlayersList, playersList);
    }

    public void setNewBattleGame(int numberPlayers, int numberRealPlayers, PlayersList realPlayersList, PlayersList AIPlayersList, PlayersList playersList){
        this.game = new BattleGame(numberPlayers,numberRealPlayers, realPlayersList, AIPlayersList, playersList);
    }
}
