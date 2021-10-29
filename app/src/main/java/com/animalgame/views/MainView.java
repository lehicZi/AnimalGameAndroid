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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.editTextManagement.EditTextGenerator;
import com.animalgame.objects.gameModes.Game;
import com.animalgame.objects.player.Player;
import com.animalgame.objects.player.PlayersList;
import com.animalgame.objects.player.RealPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainView extends AppCompatActivity {

    private EditText nbPlayersET;
    private int nbPlayers;
    private Spinner nbRealPlayersS;
    private int nbRealPlayers = 0;
    private Button gameModeBattleB;
    private Button gameModeOneDeckB;
    private ScrollView playersNamesSV;
    private TextView stringAskPlayersNames;

    private Game game = DataShared.getInstance().getGame();

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
        this.editTextGenerator = new EditTextGenerator(this, playersNamesSV);
    }

    private void setListeners() {
        nbPlayersET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                stringAskPlayersNames.setVisibility(View.VISIBLE);
                String saisie = s.toString();
                if (saisie.isEmpty()) {
                    nbPlayers = 0;
                    nbRealPlayersS.setAdapter(null);
                } else {
                    nbPlayers = Integer.parseInt(saisie);
                    setSpinnerValue();
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
                PlayersList realPlayers = new PlayersList();
                addRealPlayers(realPlayers);
                DataShared.getInstance().setNewBattleGame(nbPlayers, nbRealPlayers, realPlayers);
                Utils.openOtherActivity(BattleView.class, thisContext);

            }
        });

        gameModeOneDeckB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersList realPlayers = new PlayersList();
                addRealPlayers(realPlayers);
                DataShared.getInstance().setNewOneDeckGame(nbPlayers, nbRealPlayers, realPlayers);
                Utils.openOtherActivity(BattleView.class, thisContext);

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


    }

    private void setSpinnerValue() {
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

    public void addRealPlayers(PlayersList realPlayers) {
        for (final EditText editText : this.editTextGenerator.getEditTextListe()) {
            final String playerName = editText.getText().toString().trim();

            if (!playerName.isEmpty()) {
                final Player player = new RealPlayer(playerName) {
                };
                realPlayers.addPlayer(player);
            }
        }

    }
}
