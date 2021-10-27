package com.animalgame;

import android.content.Intent;
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

import com.animalgame.editTextManagement.EditTextGenerator;
import com.animalgame.editTextManagement.EditTextsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainView extends AppCompatActivity {

    private EditText nbPlayersET;
    private int nbPlayers;
    private Spinner nbRealPlayersS;
    private int nbRealPlayers = 0;
    private Button gameModeBattleB;
    private Button gameModeOneDeckB;
    private ScrollView playersNamesSV;
    private TextView stringAskPlayersNames;

    private EditTextGenerator editTextGenerator;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        this.setContentView(R.layout.main_view);

        instanciateView();

        setListeners();
    }

    private void instanciateView(){
        nbPlayersET = findViewById(R.id.nbplayers);
        nbRealPlayersS = findViewById(R.id.nbRealPlayers);
        gameModeBattleB = findViewById(R.id.gameModeBattle);
        gameModeOneDeckB = findViewById(R.id.gameModeOndeDeck);
        playersNamesSV = findViewById(R.id.scrollViewPlayersNames);
        stringAskPlayersNames = findViewById(R.id.stringPlayersNames);
        this.editTextGenerator = new EditTextGenerator(this, playersNamesSV);
    }

    private void setListeners(){
        nbPlayersET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                stringAskPlayersNames.setVisibility(View.VISIBLE);
                String saisie = s.toString();
                if (saisie.isEmpty()){
                    nbPlayers = 0;
                    nbRealPlayersS.setAdapter(null);
                }
                else {
                    nbPlayers = Integer.parseInt(saisie);
                    setSpinnerValue();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nbPlayersET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameModeBattleB.setVisibility(View.GONE);
                gameModeOneDeckB.setVisibility(View.GONE);
            }
        });

        gameModeBattleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities();

            }
        });

        gameModeOneDeckB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities();

            }
        });

        nbRealPlayersS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection =(String) parent.getItemAtPosition(position);
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

    private void setSpinnerValue(){
        List<String> propositions = new ArrayList<>();
        for (int i = 1 ; i <= nbPlayers ; i++ ){
            propositions.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, propositions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nbRealPlayersS.setAdapter(adapter);

    }


    private void createPlayersNamesFields(){
        for (int playerIndex = 0 ; playerIndex < nbRealPlayers ; playerIndex ++){
            editTextGenerator.addEditText();;
        }
    }


    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, BattleView.class);
        startActivity(switchActivityIntent);
    }

}
