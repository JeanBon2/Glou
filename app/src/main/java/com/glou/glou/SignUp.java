package com.glou.glou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Jean on 02/09/2018.
 */

public class SignUp extends AppCompatActivity {

    public static final String PLAYERS_LIST = "PlayersList";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<PlayerItem> playerItemsList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        playerItemsList = new ArrayList<>();

        playerItemsList.add(new PlayerItem());
        playerItemsList.add(new PlayerItem());
        playerItemsList.add(new PlayerItem());

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        playerItemsList.trimToSize();
        adapter = new Adapter(playerItemsList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button buttonStart = (Button) findViewById(R.id.buttonStartGame);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame();
            }
        });
    }
    private void launchGame(){
        ArrayList<String> playersList = new ArrayList<>();
        for(int i=0; i<playerItemsList.size(); i++){
            String name = ((EditText) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.name)).getText().toString();
            if(!name.isEmpty())
                playersList.add(name);
        }

        if(playersList.size()>0){
            Intent intentGameLauncher = new Intent(this, Game.class);
            intentGameLauncher.putExtra(PLAYERS_LIST,playersList);
            startActivity(intentGameLauncher);
        }

    }

}

