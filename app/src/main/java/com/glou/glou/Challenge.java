package com.glou.glou;

/**
 * Created by Jean on 13/09/2018.
 */

public class Challenge {
    private int categorie;
    private int challenge;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Challenge){
            if(!(((Challenge) obj).getChallenge() == this.challenge && ((Challenge) obj).getCategorie() == this.categorie)){
                return false;
            }
        }
        else{
            return false;
        }

        return true;
    }

    public Challenge(int categorie, int challenge) {
        this.categorie = categorie;
        this.challenge = challenge;
    }

    public int getCategorie() {
        return categorie;
    }

    public int getChallenge() {
        return challenge;
    }
}
