package com.animalgame.constant;

import android.net.Uri;

import com.animalgame.R;
import com.animalgame.objects.Animal;

public class  Rarete {
    /**Classe utilitaire permettant de stocker les valeurs possibles des raretés
     */

    public static final int VERT = 0;
    public static final int JAUNE = 1;
    public static final int ORANGE = 2;
    public static final int ROUGE = 3;

    /**Permet de convertir la valeur numérique de la rareté en String représentant la couleur associée.
     */

    private Rarete() {
    }

    public static String nameRarete(int rarete){
            switch (rarete) {
                case VERT : return "vert";
                case JAUNE : return "jaune";
                case ORANGE : return "orange";
                case ROUGE : return "rouge";
                default : throw new IllegalStateException();
        }
    }

    public static Uri getRareteVisual(Animal animal){

        switch (animal.getRarete()){
            case VERT : {
                return Uri.parse("android.resource://com.animalgame/" + R.raw.rarete_vert);
            }
            case JAUNE: {
                return Uri.parse("android.resource://com.animalgame/" + R.raw.rarete_jaune);
            }
            case ORANGE: {
                return Uri.parse("android.resource://com.animalgame/" + R.raw.rarete_orange);
            }
            case ROUGE: {
                return Uri.parse("android.resource://com.animalgame/" + R.raw.rarete_rouge);
            }
            default:{
                throw new IllegalStateException("La rareté demandée n'existe pas !");
            }

        }

    }
}
