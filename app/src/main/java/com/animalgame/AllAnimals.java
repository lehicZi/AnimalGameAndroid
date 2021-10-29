package com.animalgame;

import java.util.ArrayList;
import java.util.List;


import com.animalgame.objects.Animal;
import com.animalgame.constant.Rarete;

public class AllAnimals {

    /** Classe permettant de stocker les animaux.
     * allAimals est la liste de tous les animaux présents en jeu.
     * Remplacer par une BDD ?
     */

    private final ArrayList<Animal> allAnimals;

    /** Constructeur de la classe
     *  Créé la liste puis y ajoute les animaux grâce à la méthode instanciateAnimals().
     */

    public AllAnimals() {
        this.allAnimals = new ArrayList<>();
        instanciateAnimals();
    }

    /** Permet de créer la liste allAnimals, tous les animaux présents en jeu sont à instancier ici dans un premier temps,
     * puis à ajouter à la liste.
     */

    public void instanciateAnimals() {
        // On instancie les animaux.
        Animal meduseLion = new Animal("Méduse Lion", 600d, 125d, 1d, 10d, Rarete.VERT,res.drawable. )

        // On les ajoute à la liste.
        allAnimals.add(test);
        allAnimals.add(test2);
        allAnimals.add(opossum);
        allAnimals.add(gecko);
        allAnimals.add(moucherolle);
        allAnimals.add(impala);
    }

    // Getter

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }
}
