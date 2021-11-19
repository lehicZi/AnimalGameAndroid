package com.animalgame.views.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animalgame.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewholderForRecycler> {

    Context context;
    List<RecyclerItems> items;

    public Adapter(Context context, List<RecyclerItems> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewholderForRecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewholderForRecycler(LayoutInflater.from(context).inflate(R.layout.items_for_after_choice_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderForRecycler holder, int position) {

        holder.indication.setText(items.get(position).getIndication());
        holder.cardVisual.setImageURI(items.get(position).getVisual());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
