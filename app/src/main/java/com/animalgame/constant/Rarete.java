package com.animalgame.constant;

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
}
