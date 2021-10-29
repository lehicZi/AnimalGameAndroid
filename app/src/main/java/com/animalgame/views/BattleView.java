package com.animalgame.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.objects.gameModes.Game;

import java.util.List;

public class BattleView extends AppCompatActivity {

    private TextView indicationTV;

    private DataShared dataShared = DataShared.getInstance();
    private Game game = dataShared.getGame();



    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.battle_view);

        instanciateView();
        setIndicationTV(game.getPlayersList().toString());

    }

    private void instanciateView(){

        indicationTV = findViewById(R.id.indicationText);

    }

    private void setIndicationTV (String indication){

        indicationTV.setText(indication);

    }



}