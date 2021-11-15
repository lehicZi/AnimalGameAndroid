package com.animalgame.views.Recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animalgame.R;

public class ViewholderForRecycler extends RecyclerView.ViewHolder {

    ImageView cardVisual;
    TextView indication;


    public ViewholderForRecycler(@NonNull View itemView) {
        super(itemView);
        cardVisual = itemView.findViewById(R.id.cardVisual);
        indication = itemView.findViewById(R.id.indication);
    }
}
