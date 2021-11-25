package com.animalgame.views.animalsList.Recycler;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animalgame.DataBase;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;
import com.animalgame.views.animalsList.AnimalView;
import com.animalgame.views.animalsList.AnimalsListSingleton;
import com.animalgame.views.animalsList.AnimalsListView;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<ViewHolderForAnimalsRecycler> {

    Context context;
    List<AnimalsRecyclerItems> items;


    public AnimalsAdapter(Context context, List<AnimalsRecyclerItems> items) {
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolderForAnimalsRecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderForAnimalsRecycler(LayoutInflater.from(context).inflate(R.layout.items_for_animal_list_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForAnimalsRecycler holder, int position) {

        holder.useS.setChecked(items.get(position).used);

        setDefault(holder);

        holder.animalsNom.setText(items.get(position).getAnimalsNom());
        holder.animalsRarete.setImageURI(items.get(position).getAnimalsRarete());
        holder.animalsPoids.setText(items.get(position).getAnimalsPoids());
        holder.animalsLongueur.setText(items.get(position).getAnimalsLongueur());
        holder.animalsLongevite.setText(items.get(position).getAnimalsLongevite());
        holder.animalsGestation.setText(items.get(position).getAnimalsGestation());

        setListeners(holder);



    }

    private void setDefault(ViewHolderForAnimalsRecycler holder){

        int pos = holder.getAbsoluteAdapterPosition();

        if (items.get(pos).used){
            holder.useS.setText("Utilisé ");
        }
        else {
            holder.useS.setText("Non utilisé ");
        }

    }


    private void setListeners(ViewHolderForAnimalsRecycler holder){

        int pos = holder.getAbsoluteAdapterPosition();
        Animal currentAnimal = items.get(pos).getAnimal();


        holder.useS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                if (isChecked){

                    holder.useS.setText("Utilisé ");
                    currentAnimal.setUsed(true);

                }

                else{

                    holder.useS.setText("Non utilisé ");
                    currentAnimal.setUsed(false);

                }

                DataBase.updateUse((Activity) context, currentAnimal);

            }
        });


        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimalsListSingleton.getInstance().setSelectedAnimal(currentAnimal);
                AnimalsListSingleton.getInstance().setPosition(pos);
                Utils.openOtherActivity(AnimalView.class, (Activity) context);


            }
        });



    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<AnimalsRecyclerItems> items) {
        this.items = items;
    }
}
