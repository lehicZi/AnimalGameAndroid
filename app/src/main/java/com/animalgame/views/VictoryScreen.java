package com.animalgame.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.objects.player.Player;

import java.util.List;

public class VictoryScreen extends AppCompatActivity {

    private TextView winnerIndication;
    private TextView victoriesIndication;

    private Button goToMainViewB;
    private Button closeAppB;

    Game currentGame = DataShared.getInstance().getGame();


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.victory_screen);

        instanciateView();

        setListeners();

        setIndications();

        setTitle("Fin de partie");
    }

    private void instanciateView(){

        winnerIndication = findViewById(R.id.winnerAnonce);
        victoriesIndication = findViewById(R.id.victoriesAnonce);

        goToMainViewB = findViewById(R.id.buttonGoMainView);
        closeAppB = findViewById(R.id.buttonClose);

    }

    private void setListeners(){
        goToMainViewB.setOnClickListener(v -> {

//            DataShared.getInstance().resetGame();

            Utils.openOtherActivity(MainView.class, this);

        });

        closeAppB.setOnClickListener(v -> {

//            DataShared.getInstance().resetGame();

            this.finish();

        });


    }

    public void setIndications(){

        List<Player> finalWinners = this.currentGame.findFinalWinner();

        if (finalWinners.size() == 1){

            this.winnerIndication.setText("Félicitations à " + finalWinners.get(0) + " qui a gagné la partie !");

        }
        else {

            StringBuilder winnersAnnonce = new StringBuilder();
            String separator = "";
            for (Player winner : finalWinners){

                winnersAnnonce.append(separator);
                winnersAnnonce.append(winner.getPlayerName());

                separator = " et ";

            }

            this.winnerIndication.setText("Félicitations à " + winnersAnnonce + " qui ont gagné la partie à égalité !");

        }
        this.victoriesIndication.setText("Avec " + finalWinners.get(0).getVictories() + " manches remportées !");


    }

}
