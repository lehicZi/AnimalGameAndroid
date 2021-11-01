package com.animalgame.views;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.editTextManagement.EditTextGenerator;
import com.animalgame.objects.Animal;
import com.animalgame.objects.Deck;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.objects.player.AIPlayer;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;
import com.animalgame.objects.player.RealPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainView extends AppCompatActivity {

    private EditText nbPlayersET;
    private int nbPlayers;
    private Spinner nbRealPlayersS;
    private int nbRealPlayers = 0;
    private Button gameModeBattleB;
    private Button gameModeOneDeckB;
    private ScrollView playersNamesSV;
    private TextView stringAskPlayersNames;

    private RadioGroup starterRG;
    private TextView whoStartsTV;
    private TextView whoRealTV;
    private Spinner realStarterS;

    private Game game = DataShared.getInstance().getGame();
    private PlayersList realPlayers;
    private PlayersList AIPlayers;
    private Player starter;

    private EditTextGenerator editTextGenerator;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.main_view);

        instanciateView();

        setListeners();
    }

    private void instanciateView() {
        nbPlayersET = findViewById(R.id.nbplayers);
        nbRealPlayersS = findViewById(R.id.nbRealPlayers);
        gameModeBattleB = findViewById(R.id.gameModeBattle);
        gameModeOneDeckB = findViewById(R.id.gameModeOndeDeck);
        playersNamesSV = findViewById(R.id.scrollViewPlayersNames);
        stringAskPlayersNames = findViewById(R.id.stringPlayersNames);

        starterRG = findViewById(R.id.radioGroupStarter);
        whoStartsTV = findViewById(R.id.stringStarter);
        whoRealTV = findViewById(R.id.stringStarterPlayer);
        realStarterS = findViewById(R.id.realStarter);

        this.editTextGenerator = new EditTextGenerator(this, playersNamesSV);
        this.realPlayers = new PlayersList();
        this.AIPlayers = new PlayersList();
    }

    private void setListeners() {
        nbPlayersET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                stringAskPlayersNames.setVisibility(View.VISIBLE);
                starterRG.setVisibility(View.VISIBLE);
                whoStartsTV.setVisibility(View.VISIBLE);

                String saisie = s.toString();
                if (saisie.isEmpty()) {
                    nbPlayers = 0;
                    nbRealPlayersS.setAdapter(null);
                } else {
                    nbPlayers = Integer.parseInt(saisie);
                    setNumberPlayersSpinnerValue();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Context thisContext = this;

        gameModeBattleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRealPlayers();

                createAIPlayersList();

                PlayersList playersList = new PlayersList();
                playersList.addAllPlayers(realPlayers);
                playersList.addAllPlayers(AIPlayers);

                try {
                    playersList.giveOrder(starter);
                    playersList.orderPlayersList();

                    DataShared.getInstance().setNewBattleGame(nbPlayers, nbRealPlayers, realPlayers, AIPlayers, playersList);
                    Utils.openOtherActivity(BattleView.class, thisContext);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    Utils.showMessage("erreur", "Le joueur désigné pour commencer n'existe plus !", thisContext);
                    setRealStarterSpinnerValue();
                }



            }
        });

        gameModeOneDeckB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRealPlayers();

                createAIPlayersList();

                PlayersList playersList = new PlayersList();
                playersList.addAllPlayers(realPlayers);
                playersList.addAllPlayers(AIPlayers);

                try {
                    playersList.giveOrder(starter);
                    playersList.orderPlayersList();

                    DataShared.getInstance().setNewOneDeckGame(nbPlayers, nbRealPlayers, realPlayers, AIPlayers, playersList);
                    Utils.openOtherActivity(BattleView.class, thisContext);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    Utils.showMessage("erreur", "Le joueur désigné pour commencer n'existe plus !", thisContext);
                    setRealStarterSpinnerValue();
                }


            }
        });

        nbRealPlayersS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                nbRealPlayers = Integer.parseInt(selection);
                editTextGenerator.clearLayout();
                editTextGenerator.clearEditTextList();
                createPlayersNamesFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        realStarterS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                for (Player player : realPlayers.getallPlayers()){
                    if (player.equals(selection)){
                        starter = player;
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setNumberPlayersSpinnerValue() {
        List<String> propositions = new ArrayList<>();
        for (int i = 1; i <= nbPlayers; i++) {
            propositions.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, propositions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nbRealPlayersS.setAdapter(adapter);

    }



    private void createPlayersNamesFields() {
        for (int playerIndex = 0; playerIndex < nbRealPlayers; playerIndex++) {
            editTextGenerator.addEditText();
        }
    }


    public void addRealPlayers() {

        PlayersList newRealPlayers = new PlayersList();

        for (final EditText editText : this.editTextGenerator.getEditTextListe()) {
            final String playerName = editText.getText().toString().trim();

            if (!playerName.isEmpty()) {
                final Player player = new RealPlayer(playerName) {
                };
                newRealPlayers.addPlayer(player);
            }
        }
        this.realPlayers = newRealPlayers;

    }

    public void createAIPlayersList() {
        Player player;
        String AIsname;
        PlayersList newAIPlayersList = new PlayersList();
        for (int nameIndex = 0; nameIndex < (nbPlayers-nbRealPlayers); nameIndex++) {
            AIsname = "Bot " + (nameIndex + 1);
            player = new AIPlayer(AIsname);
            newAIPlayersList.addPlayer(player);
        }
        this.AIPlayers = newAIPlayersList;

    }

    private void choseBotStater() {
        Random random = new Random();
        this.starter = this.AIPlayers.getPlayer(random.nextInt(nbPlayers - nbRealPlayers));
    }

    public void checkRadioButton(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioHumainPlayer:
                if (checked){
                    gameModeBattleB.setVisibility(View.VISIBLE);
                    gameModeOneDeckB.setVisibility(View.VISIBLE);

                    whoRealTV.setVisibility(View.VISIBLE);
                    realStarterS.setVisibility(View.VISIBLE);

                    addRealPlayers();
                    setRealStarterSpinnerValue();

                }
                    break;
            case R.id.radioAIPlayer:
                if (checked){
                    gameModeBattleB.setVisibility(View.VISIBLE);
                    gameModeOneDeckB.setVisibility(View.VISIBLE);

                    whoRealTV.setVisibility(View.INVISIBLE);
                    realStarterS.setVisibility(View.INVISIBLE);

                    createAIPlayersList();

                    choseBotStater();

                }
                    break;
        }
    }

    private void setRealStarterSpinnerValue() {
        List<String> propositions = new ArrayList<>();
        for (Player player : realPlayers.getallPlayers()) {
            propositions.add(player.getPlayerName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, propositions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        realStarterS.setAdapter(adapter);

    }


}
