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

    /**Classe utilisée pour gérer la base de donnée stockant les cartes, et permettant d'en activer/désactiver

     */


    public static final String ANIMAL_TABLE = "ANIMAL_TABLE";
    public static final String COLUMN_NOM = "NOM";
    public static final String COLUMN_ISUSED = "ISUSED";


    public DataBase(@Nullable Context context) {
        super(context, "animals.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ANIMAL_TABLE +
                " (" + COLUMN_NOM + " TEXT PRIMARY KEY," +
                " " + COLUMN_ISUSED + " BOOL NOT NULL)";
        db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ANIMAL_TABLE);
        onCreate(db);

    }



    private static boolean addEntry(Activity activity, Animal animal){

        final SQLiteDatabase database = new DataBase(activity).getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NOM, animal.getNom());
        contentValues.put(COLUMN_ISUSED, animal.isUsed());


        long insert = database.insert(ANIMAL_TABLE, null, contentValues);

        database.close();
        return insert != -1;

    }

    private static boolean isAnEntry(Activity activity, Animal animal){

        final SQLiteDatabase database = new DataBase(activity).getReadableDatabase();

        String query = "SELECT " + COLUMN_NOM + " FROM " + ANIMAL_TABLE + " WHERE " + COLUMN_NOM + " =?";
        String[] arg = {animal.getNom()};
        Cursor cursor = database.rawQuery(query, arg);

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

    private static Animal getFromName(String name, List<Animal> animalList){

        for (Animal animal : animalList){

            if (animal.getNom().equals(name)){
                return  animal;
            }

        }

        throw new IllegalStateException("Il n'y pas l'animal recherché dans la liste !");

    }

    public static void updateAnimalList(Activity activity, List<Animal> animalList){

        String query = "SELECT * FROM " + ANIMAL_TABLE;
        final SQLiteDatabase database = new DataBase(activity).getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext())
        {

            String nom = cursor.getString(0);
            boolean isused = cursor.getInt(1) == 1;

            Animal currentAnimal = getFromName(nom, animalList);
            currentAnimal.setUsed(isused);

        }
        cursor.close();
        database.close();


    }

    public static boolean updateUse(Activity activity,Animal animal){

        final SQLiteDatabase database = new DataBase(activity).getWritableDatabase();


        final String where = COLUMN_NOM + "=?";
        final String[] whereArgs = {animal.getNom()};
        final ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ISUSED, animal.isUsed());

        final boolean updated = database.update(ANIMAL_TABLE, contentValues, where, whereArgs) != -1;

        database.close();


        return updated;

    }
}
