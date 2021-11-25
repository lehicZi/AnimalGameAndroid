package com.animalgame.views.animalsList;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.constant.Rarete;
import com.animalgame.objects.Animal;
import com.animalgame.views.Recycler.RecyclerItems;
import com.animalgame.views.animalsList.Recycler.AnimalsAdapter;
import com.animalgame.views.animalsList.Recycler.AnimalsRecyclerItems;

import java.util.ArrayList;
import java.util.List;

public class AnimalsListView extends AppCompatActivity {

    private RecyclerView recyclerView;

    private AnimalsAdapter adapter;

    private List<AnimalsRecyclerItems> recyclerItemsList;

    private AnimalsListViewManager animalsListViewManager;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.animals_list_view);
        this.setTitle("Animaux");

        instanciateView();

        setRecyclerItems(animalsListViewManager.getAnimalListToShow());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.sort_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if (!searchView.isIconified())
                {
                    searchView.setIconified(true);
                }

                searchItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String query)
            {

                animalsListViewManager.sortBySearch(query);
                updateRecycler();

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        final int item_id = item.getItemId();

        if (item_id == R.id.tri_alphabetique){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.ALPHABETIC);
            animalsListViewManager.sortBy(SortMods.ALPHABETIC);
            Utils.reloadView(this, AnimalsListView.class);
            Toast.makeText(AnimalsListView.this, "Liste triée par ordre alphabétique", Toast.LENGTH_SHORT).show();


        }

        if (item_id == R.id.tri_use){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.ISUSED);
            animalsListViewManager.sortBy(SortMods.ISUSED);
            updateRecycler();
            Toast.makeText(AnimalsListView.this, "Liste triée par utilisation animaux (utilisés en haut)", Toast.LENGTH_SHORT).show();


        }

        else if (item_id == R.id.tri_rarete){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.RARETE);
            animalsListViewManager.sortBy(SortMods.RARETE);
            updateRecycler();
            Toast.makeText(AnimalsListView.this, "Liste triée par rareté", Toast.LENGTH_SHORT).show();

        }

        if (item_id == R.id.tri_poids){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.POIDS);
            animalsListViewManager.sortBy(SortMods.POIDS);
            updateRecycler();
            Toast.makeText(AnimalsListView.this, "Liste triée par poids", Toast.LENGTH_SHORT).show();

        }

        if (item_id == R.id.tri_longueur){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.LONGUEUR);
            animalsListViewManager.sortBy(SortMods.LONGUEUR);
            updateRecycler();
            Toast.makeText(AnimalsListView.this, "Liste triée par longueur", Toast.LENGTH_SHORT).show();

        }

        if (item_id == R.id.tri_longevite){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.LONGEVITE);
            animalsListViewManager.sortBy(SortMods.LONGEVITE);
            updateRecycler();
            Toast.makeText(AnimalsListView.this, "Liste triée par longévité", Toast.LENGTH_SHORT).show();

        }

        if (item_id == R.id.tri_gestation){

            AnimalsListSingleton.getInstance().setSortMod(SortMods.GESTATION);
            animalsListViewManager.sortBy(SortMods.GESTATION);
            updateRecycler();
            Toast.makeText(AnimalsListView.this, "Liste triée par gestation/incubation", Toast.LENGTH_SHORT).show();

        }


        return super.onOptionsItemSelected(item);
    }


    private void instanciateView(){

        instanciateRecycler();

        animalsListViewManager = new AnimalsListViewManager();

    }

    private void instanciateRecycler(){

        this.recyclerItemsList = new ArrayList<>();
        this.adapter = new AnimalsAdapter(this, recyclerItemsList);

        recyclerView = findViewById(R.id.animalsRecycler);


        recyclerView.setLayoutManager((new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)));
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    private void updateRecycler(){

        recyclerItemsList.clear();
        setRecyclerItems(animalsListViewManager.getAnimalListToShow());
        adapter.setItems(recyclerItemsList);
        recyclerView.setAdapter(adapter);

    }


    private void setRecyclerItems(List<Animal> listToShow){


        for (Animal animal : listToShow){

            AnimalsRecyclerItems animalInfos = new AnimalsRecyclerItems(
                    animal.getNom(),
                    "Rareté : " + Rarete.nameRarete(animal.getRarete()),
                    "Poids : " + animal.getPoids() + " kg",
                    "Longueur : " + animal.getLongueur() + " cm",
                    "Longévité : " + animal.getLongevite() + " ans",
                    "Gestion/incubation : " + animal.getGestationIncubation() + " jours",
                    animal.isUsed(),
                    animal
            );

            this.recyclerItemsList.add(animalInfos);


        }

    }


}
