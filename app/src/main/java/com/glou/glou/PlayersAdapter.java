package com.glou.glou;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Property;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jean on 08/09/2018.
 */

class PlayersAdapter extends ArrayAdapter<String> {

    private ArrayList<String> playersList;

    //constructor, call on creation
    public PlayersAdapter(Context context, int resource, ArrayList<String> playersList) {
        super(context, resource, playersList);
        this.playersList = playersList;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_player,parent,false);
        final EditText editTextPlayersName = view.findViewById(R.id.name);
        editTextPlayersName.setText(playersList.get(position));
        return view;
    }
}
