package com.animalgame.views.animalsList.Recycler;

import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.animalgame.R;

public class ViewHolderForAnimalsRecycler extends RecyclerView.ViewHolder {

    Switch useS;
    TextView animalsNom, animalsPoids, animalsLongueur, animalsLongevite, animalsGestation;
    ImageView animalsRarete;
    RelativeLayout itemLayout;


    public ViewHolderForAnimalsRecycler(@NonNull View itemView) {
        super(itemView);
         useS = itemView.findViewById(R.id.usedSwitch);
         animalsNom = itemView.findViewById(R.id.animalName);
         animalsRarete = itemView.findViewById(R.id.animalRarete);
         animalsPoids = itemView.findViewById(R.id.animalPoids);
         animalsLongueur = itemView.findViewById(R.id.animalLongueur);
         animalsLongevite = itemView.findViewById(R.id.animalLongevite);
         animalsGestation = itemView.findViewById(R.id.animalGestation);
         itemLayout = itemView.findViewById(R.id.recyclerLayout);


    }

}
