package com.animalgame.views.animalsList.Recycler;

import android.net.Uri;

import com.animalgame.objects.Animal;

public class AnimalsRecyclerItems {

    String animalsNom, animalsPoids, animalsLongueur, animalsLongevite, animalsGestation;
    Uri animalsRarete;
    boolean used;
    Animal animal;

    public AnimalsRecyclerItems(String animalsNom, Uri animalsRarete, String animalsPoids, String animalsLongueur, String animalsLongevite, String animalsGestation, boolean used, Animal animal) {
        this.animalsNom = animalsNom;
        this.animalsRarete = animalsRarete;
        this.animalsPoids = animalsPoids;
        this.animalsLongueur = animalsLongueur;
        this.animalsLongevite = animalsLongevite;
        this.animalsGestation = animalsGestation;
        this.used = used;
        this.animal = animal;
    }

    public String getAnimalsNom() {
        return animalsNom;
    }

    public void setAnimalsNom(String animalsNom) {
        this.animalsNom = animalsNom;
    }

    public Uri getAnimalsRarete() {
        return animalsRarete;
    }

    public void setAnimalsRarete(Uri animalsRarete) {
        this.animalsRarete = animalsRarete;
    }

    public String getAnimalsPoids() {
        return animalsPoids;
    }

    public void setAnimalsPoids(String animalsPoids) {
        this.animalsPoids = animalsPoids;
    }

    public String getAnimalsLongueur() {
        return animalsLongueur;
    }

    public void setAnimalsLongueur(String animalsLongueur) {
        this.animalsLongueur = animalsLongueur;
    }

    public String getAnimalsLongevite() {
        return animalsLongevite;
    }

    public void setAnimalsLongevite(String animalsLongevite) {
        this.animalsLongevite = animalsLongevite;
    }

    public String getAnimalsGestation() {
        return animalsGestation;
    }

    public void setAnimalsGestation(String animalsGestation) {
        this.animalsGestation = animalsGestation;
    }


    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Animal getAnimal() {
        return animal;
    }
}
