package com.animalgame.objects;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;

import com.animalgame.objects.player.Player;

public class Animal{
    /**Classe permettant de définir ce qu'est un animal.
     * nom est son nom.
     * poids, longueur, longevite, et gestationIncubation sont les 4 attributs permettant de le comparer.
     * rareté est un attribut supplémentaire, utilisé pour le changement d'attribut et si la comparaison
     * sur l'un des attribut précedents se solde sur une égalité.
     * owner est le joueur auquel appartient l'animal.
     */

    private final String nom;
    private final double poids;
    private final double longueur;
    private final double longevite;
    private final double gestationIncubation;
    private final int rarete;
    private final Resources visual;
    private Player owner;

    /**Constructeur de la classe
     * Créé l'animal en fonction avec les caractéristiques données en paramètre.
     * @param nom son nom
     * @param poids son poids
     * @param longueur sa longueur
     * @param longevite sa longévité
     * @param gestation_incubation son temps de gestion/d'incubation
     * @param rarete sa rareté
     */

    public Animal(String nom, double poids, double longueur, double longevite, double gestation_incubation, int rarete, Resources visual) {
        this.nom = nom;
        this.poids = poids;
        this.longueur = longueur;
        this.longevite = longevite;
        this.gestationIncubation = gestation_incubation;
        this.rarete = rarete;
        this.visual = visual;
    }

    /**Permet de récupérer la valeur d'un attribut en fonction d'un code
     * @param attribute le code de l'attribut voulu
     * @return la valeur de l'attribut
     */

    public double getAttribute(int attribute){
        switch (attribute) {
            case 1 : {
                return poids;
            }
            case 2 : {
                return longueur;
            }
            case 3 : {
                return longevite;
            }
            case 4 : {
                return gestationIncubation;
            }
            default : throw new IllegalStateException();
        }
    }

    /**Permet de récupérer la nom d'un attribut en fonction d'un code
     * @param attribute le code de l'attribut voulu
     * @return le nom de l'attribut
     */

    public String getAttributesName(int attribute){
        switch (attribute) {
            case 1 : {
                return "poids";
            }
            case 2 : {
                return "longueur";
            }
            case 3 : {
                return "longévité";
            }
            case 4 : {
                return "gestion/incubation";
            }
            default : throw new IllegalStateException();
        }
    }

    //Getters
    public String getNom() {
        return nom;
    }

    public double getPoids() {
        return poids;
    }

    public double getLongueur() {
        return longueur;
    }

    public double getLongevite() {
        return longevite;
    }

    public double getGestationIncubation() {
        return gestationIncubation;
    }

    public int getRarete() {
        return rarete;
    }

    public Player getOwner() {
        return owner;
    }

    //Setter
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**Redéfinission de toString()
     * @return le nom et les caractéristiques de l'animal
     */

    @Override
    public String toString(){
        return nom + " : poids = " + poids + " kg, longueur = " + longueur + " m, longévité = " + longevite + " ans, gestation/incubation = " + gestationIncubation + " jours." ;
    }

}
