package com.glou.glou;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Jean on 02/09/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<PlayerItem> playersList;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) { super(itemView); }
    }
    public Adapter(ArrayList<PlayerItem> playersList){this.playersList = playersList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_player,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //PlayerItem currentPlayer = playersList.get(position);
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }
}
