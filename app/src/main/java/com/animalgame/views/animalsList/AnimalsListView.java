package com.animalgame.views.animalsList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalsListView extends AppCompatActivity {


    private ListView animalsLV;

    private List<Animal> allAnimals;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.animals_list_view);
        this.setTitle("Animaux");

        instanciateView();

        setListViewItems();

        setListeners();
    }


    private void instanciateView(){

        allAnimals = DataShared.getInstance().getAllAnimals();

        animalsLV = findViewById(R.id.animalListView);

    }

    private void setListViewItems(){

        ArrayAdapter<Animal> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.allAnimals);

        animalsLV.setAdapter(listAdapter);

    }

    private void setListeners(){

        this.animalsLV.setOnItemClickListener((parent, view, position, id) ->
        {
            final Animal animal = this.allAnimals.get(position);

            AnimalsListSingleton.getInstance().setSelectedAnimal(animal);
            AnimalsListSingleton.getInstance().setPosition(position);

            final Intent intent = new Intent (this, AnimalView.class);
            this.startActivity(intent);
        });


    }

}
