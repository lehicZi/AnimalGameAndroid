package com.animalgame.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;
import com.animalgame.objects.gameModes.Fight;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;

import java.util.ArrayList;
import java.util.List;

public class BattleView extends AppCompatActivity {

    private TextView indicationTV;
    private TextView indicationAutreTV;
    private Button poidsB;
    private Button longueurB;
    private Button longeviteB;
    private Button gestationB;

    private DataShared dataShared = DataShared.getInstance();
    private Game game = dataShared.getGame();

    private Integer codeEffectiveAttribute;
    private int currentFight =0;



    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.battle_view);

        instanciateView();
        setIndicationTV(game.getPlayersList().toString());

        showIndications(game.getRealPlayersList().getPlayer(0));

        setListeners();

    }

    private void instanciateView(){

        indicationTV = findViewById(R.id.indicationTextplayer);
        indicationAutreTV = findViewById(R.id.indicationTextOthers);
        poidsB = (Button) findViewById(R.id.buttonPoids);
        longueurB = (Button) findViewById(R.id.buttonLongueur);
        longeviteB = (Button) findViewById(R.id.buttonLongevite);
        gestationB = (Button) findViewById(R.id.buttonGestation);

    }

    private void setListeners(){

        poidsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codeEffectiveAttribute = 1;
                oneBattle(currentFight);
                currentFight ++;
                showIndications(game.getRealPlayersList().getPlayer(0));

            }

        });

        longueurB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codeEffectiveAttribute = 2;
                oneBattle(currentFight);
                currentFight ++;
                showIndications(game.getRealPlayersList().getPlayer(0));

            }

        });

        longeviteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codeEffectiveAttribute = 3;
                oneBattle(currentFight);
                currentFight ++;
                showIndications(game.getRealPlayersList().getPlayer(0));

            }

        });

        gestationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codeEffectiveAttribute = 4;
                oneBattle(currentFight);
                currentFight ++;
                showIndications(game.getRealPlayersList().getPlayer(0));

            }

        });

    }

    private void setIndicationTV (String indication){

        indicationTV.setText(indication);

    }

    private void showIndications(Player player){

        Animal animal = player.getPlayersInitialDeck().getListCards().get(currentFight);

        indicationAutreTV.setText("C'est à toi de choisir, " + player.getPlayerName());
        indicationTV.setText("Ton animal est : " + animal.getNom());

        poidsB.setText("Poids : " + animal.getPoids() + " kg");
        longueurB.setText("Longueur : " + animal.getLongueur() + " cm");
        longeviteB.setText("Longévité : " + animal.getLongevite() + " ans");
        gestationB.setText("Gestion/Incubation : " + animal.getGestationIncubation() + " j");

    }

    private void battle (){
        // On parcourt les cartes, currentFight représente une manche.
        for (int currentFight = 0; currentFight < game.getNumberCardsPerplayer(); currentFight++) {
            game.getPlayersList().orderPlayersList();
            List<Animal> fightingAnimals = new ArrayList<>();
            StringBuilder annonce = new StringBuilder();
            // Le joueur qui commence est le premier joueur de la liste ordonnée, donc le joueur avec dont l'attribut Order
            // vaut 1.
            Player starter = game.getPlayersList().getPlayer(0);

            game.addAnimalsAndBuildAnounce(currentFight, fightingAnimals, annonce);



            Animal animalWinner = Fight.animalFight((int)codeEffectiveAttribute, fightingAnimals);
            Player playerWinner = animalWinner.getOwner();
            Utils.showMessage("Info", "Le gagnant est " + playerWinner.getPlayerName(), this);

            game.getPlayersList().giveOrder(playerWinner);



        }
    }

    private void oneBattle (int currentFight){
        // On parcourt les cartes, currentFight représente une manche.

            game.getPlayersList().orderPlayersList();
            List<Animal> fightingAnimals = new ArrayList<>();
            StringBuilder annonce = new StringBuilder();
            // Le joueur qui commence est le premier joueur de la liste ordonnée, donc le joueur avec dont l'attribut Order
            // vaut 1.
            Player starter = game.getPlayersList().getPlayer(0);

            game.addAnimalsAndBuildAnounce(currentFight, fightingAnimals, annonce);

            Utils.showMessage("Combat", "Le combat est : " + annonce, this);

            Animal animalWinner = Fight.animalFight((int)codeEffectiveAttribute, fightingAnimals);
            Player playerWinner = animalWinner.getOwner();
            Utils.showMessage("Info", "Le gagnant est " + playerWinner.getPlayerName(), this);

            game.getPlayersList().giveOrder(playerWinner);




    }


    public TextView getIndicationTV() {
        return indicationTV;
    }

    public TextView getIndicationAutreTV() {
        return indicationAutreTV;
    }

    public Button getPoidsB() {
        return poidsB;
    }

    public Button getLongueurB() {
        return longueurB;
    }

    public Button getLongeviteB() {
        return longeviteB;
    }

    public Button getGestationB() {
        return gestationB;
    }
}