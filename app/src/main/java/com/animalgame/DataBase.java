package com.animalgame;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.animalgame.objects.Animal;
import com.animalgame.views.animalsList.AnimalView;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {


    public static final String ANIMAL_TABLE = "ANIMAL_TABLE";
    public static final String COLUMN_NOM = "NOM";
    public static final String COLUMN_POIDS = "POIDS";
    public static final String COLUMN_LONGUEUR = "LONGUEUR";
    public static final String COLUMN_LONGEVITE = "LONGEVITE";
    public static final String COLUMN_GESTATION_INCUBATION = "GESTATION_INCUBATION";
    public static final String COLUMN_RARETE = "RARETE";
    public static final String COLUMN_ISUSED = "ISUSED";
    public static final String COLUMN_FCHIER = "FCHIER";

    public DataBase(@Nullable Context context) {
        super(context, "animals.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ANIMAL_TABLE +
                " (" + COLUMN_NOM + " TEXT PRIMARY KEY," +
                " " + COLUMN_POIDS + " TEXT," +
                " " + COLUMN_LONGUEUR + " TEXT," +
                " " + COLUMN_LONGEVITE + " TEXT," +
                " " + COLUMN_GESTATION_INCUBATION + " TEXT," +
                " " + COLUMN_RARETE + " INTEGER," +
                " " + COLUMN_ISUSED + " BOOL," +
                " " + COLUMN_FCHIER + " INTEGER)";

        db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private static boolean addEntry(Activity activity, Animal animal){

        final SQLiteDatabase database = new DataBase(activity).getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NOM, Utils.getStringForDb(animal.getNom()));
        contentValues.put(COLUMN_POIDS, animal.getPoids());
        contentValues.put(COLUMN_LONGUEUR, animal.getLongueur());
        contentValues.put(COLUMN_LONGEVITE, animal.getLongevite());
        contentValues.put(COLUMN_GESTATION_INCUBATION, animal.getGestationIncubation());
        contentValues.put(COLUMN_RARETE, animal.getRarete());
        contentValues.put(COLUMN_ISUSED, animal.isUsed());
        contentValues.put(COLUMN_FCHIER, animal.getFichier());

        long insert = database.insert(ANIMAL_TABLE, null, contentValues);

        database.close();
        return insert >= 0;

    }

    private static boolean isAnEntry(Activity activity, Animal animal){

        final SQLiteDatabase database = new DataBase(activity).getReadableDatabase();

        String query = "SELECT " + COLUMN_NOM + " FROM " + ANIMAL_TABLE + " WHERE " + COLUMN_NOM + " LIKE " + "'" + Utils.getStringForDb(animal.getNom()) + "'";
        Cursor cursor = database.rawQuery(query, null);

        boolean isNotEmpty = cursor.moveToFirst();

        cursor.close();
        database.close();

        return isNotEmpty;


    }

    public static void initialInsert(Activity activity, List<Animal> allAnimals){

        for (Animal animal : allAnimals){

            if (!(isAnEntry(activity, animal))){

            addEntry(activity,animal);

            }

        }

    }

    public static List<Animal> getAllAnimals(Activity activity){

        List<Animal> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + ANIMAL_TABLE;
        final SQLiteDatabase database = new DataBase(activity).getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){

            do {
                String nom = Utils.getStringFromDb(cursor.getString(0));
                double poids = cursor.getDouble(1);
                double longueur = cursor.getDouble(2);
                double longevite = cursor.getDouble(3);
                double gestation = cursor.getDouble(4);
                int rarete = cursor.getInt(5);
                boolean isused = cursor.getInt(6) == 1;
                int fichier = cursor.getInt(7);

                Animal animal = new Animal(nom, poids, longueur, longevite, gestation, rarete, fichier);
                animal.setUsed(isused);
                returnList.add(animal);

            } while (cursor.moveToNext());


        }
        cursor.close();
        database.close();

        return returnList;

    }

    public static boolean updateUse(Activity activity,Animal animal, boolean used){

        final SQLiteDatabase database = new DataBase(activity).getWritableDatabase();


        final String where = COLUMN_NOM + "=?";
        final String[] whereArgs = {Utils.getStringForDb(animal.getNom())};
        final ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ISUSED, used);

        final boolean updated = database.update(ANIMAL_TABLE, contentValues, where, whereArgs) != -1;

        database.close();


        return updated;

    }
}
