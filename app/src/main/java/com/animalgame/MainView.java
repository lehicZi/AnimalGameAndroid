package com.animalgame;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainView extends AppCompatActivity {

    private EditText nbPlayersET;
    private int nbPlayers;
    private Spinner nbRealPlayersS;

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
    }

    private void setListeners(){
        nbPlayersET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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

}
