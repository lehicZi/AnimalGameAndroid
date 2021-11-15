package com.animalgame.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;
import com.animalgame.objects.gameModes.AttributeSwitching;
import com.animalgame.objects.gameModes.Fight;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.objects.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BattleView extends AppCompatActivity {

    private TextView indicationTV;
    private TextView indicationAutreTV;
    private Button poidsB;
    private Button longueurB;
    private Button longeviteB;
    private Button gestationB;

    private final DataShared dataShared = DataShared.getInstance();
    private final Game game = dataShared.getGame();
    private int currentPlayerIndex = 0;
    private int lastPlayerChoiceIndex =0;
    private int lastAttributeChoice;
    private int referenceRarete =0;
    private boolean wantedChange = true;

    private ImageView visual;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.battle_view);

        instanciateView();

        showIndications(game.getPlayersList().getPlayer(currentPlayerIndex));

        checkWhoStarts();
        setListeners();


    }

    private void instanciateView(){

        indicationTV = findViewById(R.id.indicationTextplayer);
        indicationAutreTV = findViewById(R.id.indicationTextOthers);
        poidsB = findViewById(R.id.buttonPoids);
        longueurB = findViewById(R.id.buttonLongueur);
        longeviteB = findViewById(R.id.buttonLongevite);
        gestationB = findViewById(R.id.buttonGestation);


        visual = findViewById(R.id.animalVisual);


    }

    private void setListeners(){

        poidsB.setOnClickListener(v -> {

          buttonsAction(1);


        });

        longueurB.setOnClickListener(v -> {

            buttonsAction(2);

        });

        longeviteB.setOnClickListener(v -> {

            buttonsAction(3);

        });

        gestationB.setOnClickListener(v -> {

            buttonsAction(4);

        });

    }


    private void showIndications(Player player){


        Animal animal = player.getPlayersInitialDeck().getListCards().get(game.getCurrentFightIndex());

        indicationAutreTV.setText("C'est à toi de choisir, " + player.getPlayerName());
//        indicationTV.setText("Ton animal est : " + animal.getNom());

        poidsB.setText("Poids : " + animal.getPoids() + " kg");
        longueurB.setText("Longueur : " + animal.getLongueur() + " cm");
        longeviteB.setText("Longévité : " + animal.getLongevite() + " ans");
        gestationB.setText("Gestion/Incubation : " + animal.getGestationIncubation() + " j");

        visual.setImageURI(animal.getVisual());

    }

    private void oneBattle (int currentFight, int codeEffectiveAttribute){
        // On parcourt les cartes, currentFight représente une manche.

            List<Animal> fightingAnimals = new ArrayList<>();

        game.addAnimals(currentFight, fightingAnimals);


        Fight fight = new Fight(fightingAnimals, codeEffectiveAttribute);
        fight.animalFight();

        game.addFightToHistory(fight);

        Player playerWinner = fight.getPlayerWinner();
        playerWinner.incrementVictories();

        game.getPlayersList().giveOrder(playerWinner);
        game.getPlayersList().orderPlayersList();

    }

    private void checkWhoStarts(){

        Player starter = game.getPlayersList().getPlayer(0);

        if (!starter.isReal()){

            poidsB.setVisibility(View.INVISIBLE);
            longueurB.setVisibility(View.INVISIBLE);
            longeviteB.setVisibility(View.INVISIBLE);
            gestationB.setVisibility(View.INVISIBLE);

            buttonsAction(starter.attributeChoice());

        }

        else {

            poidsB.setVisibility(View.VISIBLE);
            longueurB.setVisibility(View.VISIBLE);
            longeviteB.setVisibility(View.VISIBLE);
            gestationB.setVisibility(View.VISIBLE);

        }

    }


    private boolean isRarer (Animal animal, int rarete){
        return animal.getRarete() > rarete;
    }


    private void switchAttributePopUp(Player player, int currentAttribute,  AttributeSwitching currentSwitch){

        AttributeSwitching lastSwitch = this.game.getLastSwitch();

        Player lastPlayerchoice = lastSwitch.getSwitcher();

        Animal playersAnimal = player.getPlayersInitialDeck().getListCards().get(this.game.getCurrentFightIndex());
        Animal lastPlayersAnimal = lastPlayerchoice.getPlayersInitialDeck().getListCards().get(this.game.getCurrentFightIndex());


            if (player.isReal()){

                AlertDialog.Builder popUp = new AlertDialog.Builder(this);
                popUp.setTitle("Changement d'attribut");
                popUp.setMessage("L'attribut choisi par " + lastPlayerchoice + " est : "
                        + playersAnimal.getAttributesName(currentAttribute) + ". Mais " + player.getPlayerName() +
                        ", ton animal a une rareté supérieure à vert, veux-tu tenter de changer l'attribut choisi ?");
                popUp.setPositiveButton("Oui", (dialog, which) -> {

                        this.wantedChange = true;

                        showIndications(player);
                        poidsB.setVisibility(View.VISIBLE);
                        longueurB.setVisibility(View.VISIBLE);
                        longeviteB.setVisibility(View.VISIBLE);
                        gestationB.setVisibility(View.VISIBLE);
                        visual.setVisibility(View.VISIBLE);
                        indicationTV.setVisibility(View.VISIBLE);

                });
                popUp.setNegativeButton("Non", (dialog, which) -> {

                    this.wantedChange = false;
                    currentSwitch.setTheEffective(true);

                    buttonsAction(currentAttribute);

                });
                popUp.setCancelable(false);
                popUp.show();
            }


            else {


                buttonsAction(player.attributeChoice());

            }



    }



    private boolean itIsStarter (){
        return this.currentPlayerIndex == 0;
    }


    private void checkChangePossibility(Player player, int currentAttribute, AttributeSwitching currentSwitch){

        AttributeSwitching lastPlayerSwitch = game.getPenultimateSwitch();


        Player lastPlayerChoice = lastPlayerSwitch.getSwitcher();
        Player starter = this.game.getPlayersList().getPlayer(0);

        AttributeSwitching startersSwitch = this.game.getPlayersSwitch(starter);

        Animal currentPlayersAnimal = player.getPlayersInitialDeck().getListCards().get(this.game.getCurrentFightIndex());
        Animal lastPlayersAnimal = lastPlayerChoice.getPlayersInitialDeck().getListCards().get(this.game.getCurrentFightIndex());
        Animal startersAnimal = starter.getPlayersInitialDeck().getListCards().get(this.game.getCurrentFightIndex());


        if (wantedChange) {
            if (player.isReal()) {


                if (isRarer(currentPlayersAnimal, lastPlayersAnimal.getRarete()) && isRarer(currentPlayersAnimal, startersAnimal.getRarete())) {

                    AlertDialog.Builder popUpHappy = new AlertDialog.Builder(this);
                    popUpHappy.setTitle("Yes !");
                    popUpHappy.setMessage("L'attribut a bien été changé à " + currentPlayersAnimal.getAttributesName(currentAttribute) + " !");
                    popUpHappy.setNeutralButton("Super !", (dialog, which) -> {

                        currentSwitch.setTheEffective(true);

                        oneBattle(game.getCurrentFightIndex(), currentAttribute);
                        Utils.openOtherActivity(AfterPlayerChoice.class, this);

                    });
                    popUpHappy.setCancelable(false);
                    popUpHappy.show();

                } else if (!isRarer(currentPlayersAnimal, startersAnimal.getRarete())) {

                    AlertDialog.Builder popUpSad = new AlertDialog.Builder(this);
                    popUpSad.setTitle("Mince alors !");
                    popUpSad.setMessage("Désolé " + player.getPlayerName() + ", il n'est pas possbile de changer d'attribut parce que l'annimal de " +
                            starter.getPlayerName() + " est plus rare que le tien !");
                    popUpSad.setNeutralButton("D'accord :(", (dialog, which) -> {

                        startersSwitch.setTheEffective(true);

                        oneBattle(game.getCurrentFightIndex(), startersSwitch.getNewAttribute());
                        Utils.openOtherActivity(AfterPlayerChoice.class, this);

                    });
                    popUpSad.setCancelable(false);
                    popUpSad.show();
                } else {

                    AlertDialog.Builder popUpSad = new AlertDialog.Builder(this);
                    popUpSad.setTitle("Mince alors !");
                    popUpSad.setMessage("Désolé " + player.getPlayerName() + ", il n'est pas possbile de changer d'attribut parce que l'annimal de " +
                            lastPlayerChoice.getPlayerName() + " est plus rare que le tien !");
                    popUpSad.setNeutralButton("D'accord :(", (dialog, which) -> {

                        lastPlayerSwitch.setTheEffective(true);

                        oneBattle(game.getCurrentFightIndex(), lastPlayerSwitch.getNewAttribute());
                        Utils.openOtherActivity(AfterPlayerChoice.class, this);

                    });
                    popUpSad.setCancelable(false);
                    popUpSad.show();

                }
            } else {

                if (isRarer(currentPlayersAnimal, lastPlayersAnimal.getRarete()) && isRarer(currentPlayersAnimal, startersAnimal.getRarete())) {

                    AlertDialog.Builder popUpHappy = new AlertDialog.Builder(this);
                    popUpHappy.setTitle("Changement d'attribut");
                    popUpHappy.setMessage(player.getPlayerName() + " a changé l'attribut à " + currentPlayersAnimal.getAttributesName(currentAttribute) + " !");
                    popUpHappy.setNeutralButton("OK", (dialog, which) -> {

                        currentSwitch.setTheEffective(true);

                        oneBattle(game.getCurrentFightIndex(), currentAttribute);
                        Utils.openOtherActivity(AfterPlayerChoice.class, this);

                    });
                    popUpHappy.setCancelable(false);
                    popUpHappy.show();
                } else if (!isRarer(currentPlayersAnimal, startersAnimal.getRarete())) {

                    startersSwitch.setTheEffective(true);

                    oneBattle(game.getCurrentFightIndex(), startersSwitch.getNewAttribute());
                    Utils.openOtherActivity(AfterPlayerChoice.class, this);
                } else {
                    lastPlayerSwitch.setTheEffective(true);

                    oneBattle(game.getCurrentFightIndex(), lastPlayerSwitch.getNewAttribute());
                    Utils.openOtherActivity(AfterPlayerChoice.class, this);
                }

            }
        }
        else{

            currentSwitch.setTheEffective(true);

            oneBattle(game.getCurrentFightIndex(), currentAttribute);
            Utils.openOtherActivity(AfterPlayerChoice.class, this);

        }


    }

    private void buttonsAction(int buttonAttribute){

        AttributeSwitching attributeSwitching;

        if (wantedChange) {
            attributeSwitching = new AttributeSwitching(game.getPlayersList().getPlayer(currentPlayerIndex), buttonAttribute, lastAttributeChoice);
        }

        else {
            attributeSwitching = this.game.getLastSwitch();
        }

        lastPlayerChoiceIndex = currentPlayerIndex;

        boolean mayChange = false;

        for (int notStartersIndex = this.currentPlayerIndex +1; notStartersIndex < this.game.getNumberPlayers(); notStartersIndex++){

            Player notStarter = this.game.getPlayersList().getPlayer(notStartersIndex);
            Animal notStartersAnimal = notStarter.getPlayersInitialDeck().getListCards().get(this.game.getCurrentFightIndex());

            if (isRarer(notStartersAnimal, this.referenceRarete)){

                this.currentPlayerIndex = notStartersIndex;
                mayChange = true;
                this.referenceRarete = notStartersAnimal.getRarete();
                break;

            }
        }


        this.game.addSwitchToHistory(attributeSwitching);


        if (mayChange){
            poidsB.setVisibility(View.INVISIBLE);
            longueurB.setVisibility(View.INVISIBLE);
            longeviteB.setVisibility(View.INVISIBLE);
            gestationB.setVisibility(View.INVISIBLE);
            visual.setVisibility(View.INVISIBLE);
            indicationTV.setVisibility(View.INVISIBLE);

            switchAttributePopUp(game.getPlayersList().getPlayer(currentPlayerIndex), buttonAttribute, attributeSwitching);

        }

        else {

            if (itIsStarter()) {

                attributeSwitching.setTheEffective(true);

                oneBattle(game.getCurrentFightIndex(), buttonAttribute);
                Utils.openOtherActivity(AfterPlayerChoice.class, this);
            }

            else {

                checkChangePossibility(game.getPlayersList().getPlayer(currentPlayerIndex), buttonAttribute, attributeSwitching);
            }

        }

        lastAttributeChoice = buttonAttribute;
//        System.out.println(game.getAttributeHistory());


    }

}