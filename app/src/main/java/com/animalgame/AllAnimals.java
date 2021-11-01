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
        Animal meduseLion = new Animal("Méduse Lion", 600d, 125d, 1d, 10d, Rarete.VERT);
        Animal geckoFoliace = new Animal("Gecko Foliacé", 0.03d, 8d, 6d, 73d, Rarete.JAUNE );
        Animal hydopote = new Animal("Hydropote", 14d, 88d, 12d, 190d, Rarete.JAUNE);
        Animal axolotl = new Animal("Axolotl", 0.08d, 23d, 6d, 18d, Rarete.ROUGE);
        Animal lamproieMarine = new Animal("Lamproie marine", 1.8d, 80d, 8d, 38d, Rarete.VERT);
        Animal okapi = new Animal("Okapi", 275d, 220d, 33d, 458d, Rarete.ORANGE);

        // On les ajoute à la liste.

        allAnimals.add(meduseLion);
        allAnimals.add(geckoFoliace);
        allAnimals.add(hydopote);
        allAnimals.add(axolotl);
        allAnimals.add(lamproieMarine);
        allAnimals.add(okapi);
    }

    // Getter

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }
}
