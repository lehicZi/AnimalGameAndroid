package com.animalgame.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;
import com.animalgame.objects.gameModes.AttributeSwitching;
import com.animalgame.objects.gameModes.Fight;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.views.Recycler.Adapter;
import com.animalgame.views.Recycler.RecyclerItems;

import java.util.ArrayList;
import java.util.List;

public class AfterPlayerChoice extends AppCompatActivity {


    private Button nextB;
    private TextView winnerIndication;
    private TextView attributeAnonce;
    private TextView specificIndication;

    private final DataShared dataShared = DataShared.getInstance();
    private final Game currentGame = dataShared.getGame();

    List<RecyclerItems> fightAnonce;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.after_player_choice_visual);

        instanciateView();

        instanciateRecycler();

        setListeners();

    }

    private void instanciateView(){

        nextB = findViewById(R.id.nextButton);
        winnerIndication = findViewById(R.id.winneranonce);
        attributeAnonce = findViewById(R.id.attributeAnonce);
        specificIndication = findViewById(R.id.gameModeSpecificIndication);

        setIndications();

    }

    private void instanciateRecycler(){

        RecyclerView recyclerView = findViewById(R.id.fightAnonce);


        recyclerView.setLayoutManager((new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)));
        recyclerView.setAdapter(new Adapter(getApplicationContext(), fightAnonce));

    }

    private void setListeners(){


        nextB.setOnClickListener(v -> {

            this.currentGame.afterFightActions();

            if (currentGame.isFinished()){

                Utils.openOtherActivity(VictoryScreen.class, this);

            }

            else {

                Utils.openOtherActivity(BattleView.class, this);
            }

        });


    }

    private void setIndications(){

        this.fightAnonce = new ArrayList<>();

        AttributeSwitching effectiveSwitch = this.currentGame.getLastEffectiveSwitch();

        Fight lastFight = this.currentGame.getLastFight();

        this.specificIndication.setText(this.currentGame.setSpecificGameModeIndication());

        this.winnerIndication.setText(lastFight.getPlayerWinner().getPlayerName() + " a gagné avec son " + lastFight.getAnimalWinner().getNom() + " !" );

        StringBuilder fightIndication = new StringBuilder();
        fightIndication.append("- Attribut choisi par ") .append(effectiveSwitch.getSwitcher().getPlayerName()).append(" : ")
                .append(lastFight.getAnimalWinner().getAttributesName(lastFight.getEffectiveAttribute()))
                .append("\n\n").append("- Animaux : ");

        this.attributeAnonce.setText(fightIndication);

        for (Animal animal : lastFight.getFightingAnimals()){

            RecyclerItems infosAnimal = new RecyclerItems(animal.getVisual(),animal.getNom() + " de " +  animal.getOwner().getPlayerName());

            this.fightAnonce.add(infosAnimal);
        }


    }




}
