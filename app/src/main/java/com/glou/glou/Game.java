package com.glou.glou;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    private TextView textDrinker;
    private TextView textCategorie;
    private RelativeLayout relativeLayout;

    private  int[] androidColors;
    private  int nbAndroidColors;
    private String[] categorie;
    private int nbTotalChallenges=0;
    private final int maxNbSip = 6;

    private int lengthPerCategories[] = new int[6];

    private boolean drink;

    private ArrayList<String> playersList;
    private int playersNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        hideSystemUI();
        setContentView(R.layout.activity_game);

        androidColors = getResources().getIntArray(R.array.androidColors);
        nbAndroidColors = androidColors.length;

        textCategorie = findViewById(R.id.textCategorie);
        textDrinker = findViewById(R.id.textDrinker);
        relativeLayout = findViewById(R.id.relativeLayout);

        lengthPerCategories[0] = (getResources().getStringArray(R.array.Choices)).length ;
        lengthPerCategories[1] = (getResources().getStringArray(R.array.Dare)).length ;
        lengthPerCategories[2] = (getResources().getStringArray(R.array.DrinkIF)).length ;
        lengthPerCategories[3] = (getResources().getStringArray(R.array.List)).length ;
        lengthPerCategories[4] = (getResources().getStringArray(R.array.PeopleWith)).length ;
        lengthPerCategories[5] = (getResources().getStringArray(R.array.Truth)).length;
        for(int categorieLength : lengthPerCategories)
            nbTotalChallenges+=categorieLength;

        Intent intentGameLauncher =  getIntent();
        playersList = intentGameLauncher.getStringArrayListExtra(SignUp.PLAYERS_LIST);
        playersNumber= playersList.size();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){

            int randomAndroidColor = androidColors[new Random().nextInt(nbAndroidColors)];
            int randomDrinkPhrasesCategorie = new Random().nextInt(nbTotalChallenges);
            int nbSip = new Random().nextInt(maxNbSip);
            int minBorn = 0;
            int maxBorn = lengthPerCategories[0];

            int selectedCategorie=0;
            while(true){
                if(randomDrinkPhrasesCategorie >= minBorn && randomDrinkPhrasesCategorie < maxBorn){
                    break;
                }
                selectedCategorie++;
                minBorn=maxBorn;
                maxBorn+=lengthPerCategories[selectedCategorie];
            }

            String challengeToDisplay = "";
            String categorieToDisplay = "";

            switch (selectedCategorie){
                case 0 :
                    categorie = getResources().getStringArray(R.array.Choices);
                    challengeToDisplay = getString(R.string.choice_start) + categorie[new Random().nextInt(categorie.length)];
                    categorieToDisplay = getResources().getStringArray(R.array.Categories)[0];

                    challengeToDisplay += " ? " + getString(R.string.minority) + " ";
                    if(nbSip == 0)
                        challengeToDisplay += getString(R.string.end_his_drink);
                    else if (nbSip == 1)
                        challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                    else
                        challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);

                    break;
                case 1 :
                    categorie = getResources().getStringArray(R.array.Dare);
                    challengeToDisplay =  categorie[new Random().nextInt(categorie.length)];
                    challengeToDisplay = playersList.get(new Random().nextInt(playersNumber))+ ", " + challengeToDisplay;
                    categorieToDisplay = getResources().getStringArray(R.array.Categories)[1];
                    challengeToDisplay += " " + getString(R.string.or)+ " ";
                    if(nbSip == 0)
                        challengeToDisplay += getString(R.string.end_your_drink);
                    else if (nbSip == 1)
                        challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                    else
                        challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);
                    break;
                case 2 :
                    categorie = getResources().getStringArray(R.array.DrinkIF);
                    challengeToDisplay =  categorie[new Random().nextInt(categorie.length)];
                    challengeToDisplay = playersList.get(new Random().nextInt(playersNumber))+ ", " + challengeToDisplay;
                    categorieToDisplay = getResources().getStringArray(R.array.Categories)[2];
                    drink = new Random().nextBoolean();

                    challengeToDisplay += ", ";
                    if(drink){
                        if(nbSip == 0)
                            challengeToDisplay += getString(R.string.end_your_drink);
                        else if (nbSip == 1)
                            challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                        else
                            challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);
                    }

                    else{
                        if (nbSip == 0)
                            challengeToDisplay += getString(R.string.distribute) + " " + getString(R.string.a_gulp);
                        else if(nbSip == 1)
                            challengeToDisplay += getString(R.string.distribute) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                        else
                            challengeToDisplay += getString(R.string.distribute) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);
                    }

                    break;
                case 3 :
                    categorie = getResources().getStringArray(R.array.List);
                    challengeToDisplay =  categorie[new Random().nextInt(categorie.length)];
                    categorieToDisplay = getResources().getStringArray(R.array.Categories)[3];

                    challengeToDisplay = getString(R.string.cite) + " " + challengeToDisplay + ", " + getString(R.string.last_one) + " ";
                    if(nbSip==0)
                        challengeToDisplay += getString(R.string.end_his_drink);
                    else if(nbSip==1)
                        challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip)+ " " + getString(R.string.sip);
                    else
                        challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip)+ " " + getString(R.string.sips);

                    break;
                case 4 :
                    categorie = getResources().getStringArray(R.array.PeopleWith);
                    challengeToDisplay =  categorie[new Random().nextInt(categorie.length)];
                    categorieToDisplay = getResources().getStringArray(R.array.Categories)[4];

                    drink = new Random().nextBoolean();

                    challengeToDisplay = getString(R.string.the_person) + " " + challengeToDisplay +" ";
                    if(drink){
                        if(nbSip == 0)
                            challengeToDisplay += getString(R.string.end_his_drink);
                        else if (nbSip == 1)
                            challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                        else
                            challengeToDisplay += getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);
                    }

                    else{
                        if (nbSip == 0)
                            challengeToDisplay += getString(R.string.distribute) + " " + getString(R.string.a_gulp);
                        else if(nbSip == 1)
                            challengeToDisplay += getString(R.string.distribute) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                        else
                            challengeToDisplay += getString(R.string.distribute) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);
                    }


                    break;
                case 5 :
                    categorie = getResources().getStringArray(R.array.Truth);
                    challengeToDisplay =  categorie[new Random().nextInt(categorie.length)];
                    challengeToDisplay = playersList.get(new Random().nextInt(playersNumber))+ ", " + challengeToDisplay;
                    categorieToDisplay = getResources().getStringArray(R.array.Categories)[5];

                    challengeToDisplay += " " + getString(R.string.or)+ " " ;
                    if(nbSip == 0)
                        challengeToDisplay += getString(R.string.end_your_drink);
                    else if (nbSip == 1)
                        challengeToDisplay +=  getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sip);
                    else
                        challengeToDisplay +=  getString(R.string.drink) + " " + Integer.toString(nbSip) + " " + getString(R.string.sips);
                    break;
            }
            challengeToDisplay +=".";

           if(challengeToDisplay.indexOf(getString(R.string.actor))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.actor),getResources().getStringArray(R.array.Actors)[new Random().nextInt(getResources().getStringArray(R.array.Actors).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.car))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.car),getResources().getStringArray(R.array.Cars)[new Random().nextInt(getResources().getStringArray(R.array.Cars).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.continent))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.continent),getResources().getStringArray(R.array.Continents)[new Random().nextInt(getResources().getStringArray(R.array.Continents).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.color))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.color),getResources().getStringArray(R.array.EyesColours)[new Random().nextInt(getResources().getStringArray(R.array.EyesColours).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.vegetables))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.vegetables),getResources().getStringArray(R.array.Food)[new Random().nextInt(getResources().getStringArray(R.array.Food).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.operator))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.operator),getResources().getStringArray(R.array.Operators)[new Random().nextInt(getResources().getStringArray(R.array.Operators).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.school_cursus))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.school_cursus),getResources().getStringArray(R.array.SchoolCursus)[new Random().nextInt(getResources().getStringArray(R.array.SchoolCursus).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.sexual_practice))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.sexual_practice),getResources().getStringArray(R.array.SexualPractices)[new Random().nextInt(getResources().getStringArray(R.array.SexualPractices).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.singer))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.singer),getResources().getStringArray(R.array.Singers)[new Random().nextInt(getResources().getStringArray(R.array.Singers).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.sport))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.sport),getResources().getStringArray(R.array.Sports)[new Random().nextInt(getResources().getStringArray(R.array.Sports).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.game))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.game),getResources().getStringArray(R.array.VideoGames)[new Random().nextInt(getResources().getStringArray(R.array.VideoGames).length)]);
           else if(challengeToDisplay.indexOf(getString(R.string.minutes))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.minutes),Integer.toString(new Random().nextInt(60)));
           else if(challengeToDisplay.indexOf(getString(R.string.year))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.year),Integer.toString(new Random().nextInt(60)+1960));
           else if(challengeToDisplay.indexOf(getString(R.string.letter))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.letter),getResources().getStringArray(R.array.Letters)[new Random().nextInt(26)]);
           else if(challengeToDisplay.indexOf(getString(R.string.month))!=-1)
               challengeToDisplay = challengeToDisplay.replace(getString(R.string.month),getResources().getStringArray(R.array.Months)[new Random().nextInt(12)]);

           relativeLayout.setBackgroundColor(randomAndroidColor);
            textCategorie.setText(categorieToDisplay);
            textDrinker.setText(challengeToDisplay);
        }

        return true;
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

}
