package com.animalgame;

import java.util.ArrayList;
import java.util.List;


import com.animalgame.objects.Animal;
import com.animalgame.constant.Rarete;
import com.animalgame.views.MainView;

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

    private void instanciateAnimals() {


        // On instancie les animaux.
        Animal meduseLion = new Animal("Méduse Lion", 600d, 125d, 1d, 10d, Rarete.VERT, R.raw.meduse_lion);
        Animal geckoFoliace = new Animal("Gecko Foliacé", 0.03d, 8d, 6d, 73d, Rarete.JAUNE, R.raw.gecko_foliace );
        Animal hydopote = new Animal("Hydropote", 14d, 88d, 12d, 190d, Rarete.JAUNE, R.raw.hydropote);
        Animal axolotl = new Animal("Axolotl", 0.08d, 23d, 6d, 18d, Rarete.ROUGE, R.raw.axolotl);
        Animal lamproieMarine = new Animal("Lamproie marine", 1.8d, 80d, 8d, 38d, Rarete.VERT, R.raw.lamproie_marine);
        Animal okapi = new Animal("Okapi", 275d, 220d, 33d, 458d, Rarete.ORANGE, R.raw.okapi);
        Animal matamata = new Animal("Matamata", 12d, 38d, 10.5,200d, Rarete.VERT, R.raw.matamata);
        Animal nasique = new Animal("Nasique", 15d, 64d, 25d, 166d, Rarete.ORANGE, R.raw.nasique);
        Animal wetaGeant = new Animal("Weta géanté", 0.04d, 7.3d, 2.25d, 127d, Rarete.JAUNE, R.raw.weta_geant);
        Animal ornithorynque = new Animal("Ornithorynque", 1.7d, 50d, 12d, 26d, Rarete.VERT, R.raw.ornithorynque);
        Animal grenouilleVerre = new Animal("Grenouille de verre", 0.009d, 2.5d, 12.5d, 12d, Rarete.VERT, R.raw.grenouille_verre);
        Animal condylureEtoile = new Animal("Condylure étoilé", 0.05d, 19d, 2.5d, 40d, Rarete.VERT, R.raw.condylure_etoile);
        Animal requinPelerin = new Animal("Requin pèlerin", 3900d, 800d, 32d, 1080d, Rarete.JAUNE, R.raw.requin_pelerin);
        Animal galatheePoilue = new Animal("Galathée poilue", 0.02d, 3d, 3.5d, 13d, Rarete.VERT, R.raw.galathee_poilue);
        Animal rascasseVolante = new Animal("Rascasse volante", 1.2d, 33d, 10d, 4d, Rarete.VERT, R.raw.rascasse_volante);
        Animal lamantin = new Animal("Lamantin d'Amazonie", 480d, 290d, 29d, 328d, Rarete.JAUNE, R.raw.lamantin_amazonie);
        Animal grenouillePourpre = new Animal("Grouille pourpre", 0.09d, 7d, 5.5d, 100d, Rarete.ORANGE, R.raw.grenouille_pourpre);
        Animal baiji = new Animal("Baiji", 110d, 200d, 24d, 319d, Rarete.ROUGE, R.raw.baiji);
        Animal tortueCantor = new Animal("Tortue de Cantor", 70d, 190d, 11d, 61d, Rarete.ORANGE, R.raw.tortue_cantor);
        Animal RatTaupe = new Animal("Rat-taupe nu", 0.035d, 15d, 31d, 70d, Rarete.VERT, R.raw.rat_taupe);
        Animal pichi = new Animal("Pichi", 1.5d, 31d, 9d, 60d, Rarete.VERT, R.raw.pichi);
        Animal cameleonNamaqua = new Animal("Caméléon du Namaqua", 0.04d, 21d, 4d, 105d, Rarete.VERT, R.raw.cameleon_namaqua);
        Animal holothurieAnanas = new Animal("Holothurie ananas", 5d, 45d, 13d, 56d, Rarete.ORANGE, R.raw.holothurie_ananas);
        Animal elephantMer = new Animal("Éléphant de mer", 1500d, 400d, 15d, 252d, Rarete.VERT, R.raw.elephant_mer);
        Animal babiroussa = new Animal("Babiroussa", 72d, 98d, 23d, 153d, Rarete.JAUNE, R.raw.babiroussa);
        Animal sterneArctique = new Animal("Sterne Arctique", 0.1d, 36d, 34d, 21d, Rarete.VERT, R.raw.sterne_arctique);
        Animal tarsierSpectre = new Animal("Tarsier spectre", 0.12d, 12d, 10.5d, 157d, Rarete.JAUNE, R.raw.tarsier_spectre);
        Animal molochHerisse = new Animal("Moloch hérissé", 0.038d, 9d, 15d, 118d, Rarete.VERT, R.raw.moloch_herisse);
        Animal gazelleWaller = new Animal("Gazelle de Waller", 44d, 150d, 11d, 304d, Rarete.VERT, R.raw.gazelle_waller);
        Animal pangolin = new Animal("Pangolin", 10d, 53d, 10d, 130d, Rarete.ROUGE, R.raw.pangolin_malaisie);
        Animal hippocampeFeuille = new Animal("Hippocampe feuille", 0.11d, 30d, 9d, 28d, Rarete.VERT, R.raw.hippocampe_feuille);
        Animal crabeCocotier = new Animal("Crabe de cocotier", 4d, 40d, 30d, 30d, Rarete.VERT, R.raw.crabe_cocotier);
        Animal poissonChauveSouris = new Animal("Poisson chauve-souris", 1d, 22d, 11.5d, 3d, Rarete.VERT, R.raw.poisson_chauve_souris);
        Animal gretaOto = new Animal("Greta oto", 0.001d, 2.5d, 0.25d, 20d, Rarete.VERT, R.raw.greta_oto);
        Animal antennaireGeant = new Animal("Antennaire géant", 4d, 35d, 20d, 5d, Rarete.VERT, R.raw.antennaire_geant);
        Animal hoazinHuppe = new Animal("Hoazin Huppé", 0.5d, 70d, 8d, 32d, Rarete.VERT, R.raw.hoazin_huppe);

        // On les ajoute à la liste.

        allAnimals.add(meduseLion);
        allAnimals.add(geckoFoliace);
        allAnimals.add(hydopote);
        allAnimals.add(axolotl);
        allAnimals.add(lamproieMarine);
        allAnimals.add(okapi);
        allAnimals.add(matamata);
        allAnimals.add(nasique);
        allAnimals.add(wetaGeant);
        allAnimals.add(ornithorynque);
        allAnimals.add(grenouilleVerre);
        allAnimals.add(condylureEtoile);
        allAnimals.add(requinPelerin);
        allAnimals.add(galatheePoilue);
        allAnimals.add(rascasseVolante);
        allAnimals.add(lamantin);
        allAnimals.add(grenouillePourpre);
        allAnimals.add(baiji);
        allAnimals.add(tortueCantor);
        allAnimals.add(RatTaupe);
        allAnimals.add(pichi);
        allAnimals.add(cameleonNamaqua);
        allAnimals.add(holothurieAnanas);
        allAnimals.add(elephantMer);
        allAnimals.add(babiroussa);
        allAnimals.add(sterneArctique);
        allAnimals.add(tarsierSpectre);
        allAnimals.add(molochHerisse);
        allAnimals.add(gazelleWaller);
        allAnimals.add(pangolin);
        allAnimals.add(hippocampeFeuille);
        allAnimals.add(crabeCocotier);
        allAnimals.add(poissonChauveSouris);
        allAnimals.add(gretaOto);
        allAnimals.add(antennaireGeant);
        allAnimals.add(hoazinHuppe);
    }



    // Getter


    public List<Animal> getAllAnimals() {
        return allAnimals;
    }
}
