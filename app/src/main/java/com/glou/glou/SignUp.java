package com.glou.glou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jean on 02/09/2018.
 */

public class SignUp extends AppCompatActivity {

    public static final String PLAYERS_LIST = "PlayersList";
    private final int maxNbPlayers = 10;

    private ListView listView;


    private Button buttonAddPlayer;
    private Button buttonDeletePlayer;

    private ArrayList<String> playersList;
    private ArrayAdapter<String> playersListAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createEmptyList();
        buildListView();

        buttonAddPlayer = findViewById(R.id.buttonAddPlayer);
        buttonDeletePlayer = findViewById(R.id.buttonDeletePlayer);

        buttonAddPlayer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(playersList.size()<maxNbPlayers)
                    addPlayer();

            }
        });

        buttonDeletePlayer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(playersList.size()-1>0)
                    removePlayer();

            }
        });

        Button buttonStart = findViewById(R.id.buttonStartGame);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame();
            }
        });
    }

    private void createEmptyList(){
        playersList = new ArrayList<>();
        playersList.add("");
    }
    private void buildListView(){
        listView =  findViewById(R.id.listView);
        playersListAdapter = new PlayersAdapter(this, 0, playersList);
        listView.setAdapter(playersListAdapter);

    }

    private void addPlayer(){
        for(int i=0;i<playersList.size();i++){
            EditText editTextCurrentPlayer = listView.getChildAt(i).findViewById(R.id.name);
            String currentPlayersName = editTextCurrentPlayer.getText().toString();
            playersList.set(i, currentPlayersName);
        }
        playersList.add("");
        playersListAdapter.notifyDataSetChanged();
    }
    private void removePlayer(){
        for(int i=0;i<playersList.size();i++){
            EditText editTextCurrentPlayer = listView.getChildAt(i).findViewById(R.id.name);
            String currentPlayersName = editTextCurrentPlayer.getText().toString();
            playersList.set(i, currentPlayersName);
        }
        playersList.remove(playersList.size()-1);
        playersListAdapter.notifyDataSetChanged();
    }

    private void launchGame(){

        for(int i=0; i<playersList.size() ; i++){
            String name = ((EditText) listView.getChildAt(i).findViewById(R.id.name)).getText().toString();
                playersList.set(i, name);
        }
        for(int i=0; i<playersList.size() ; i++){
           if(playersList.get(i).equals("")){
               playersList.remove(i);
               i--;
               playersListAdapter.notifyDataSetChanged();
           }

        }

        if(playersList.size()>1){
            Intent intentGameLauncher = new Intent(this, Game.class);
            intentGameLauncher.putExtra(PLAYERS_LIST,playersList);
            startActivity(intentGameLauncher);

        }

    }

}

