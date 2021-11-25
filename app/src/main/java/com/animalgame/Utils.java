package com.animalgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;


import com.animalgame.objects.player.Player;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;


public class Utils {

    public static void openOtherActivity(final Class<?> classToOpen, final Activity activity)
    {
        final Intent intent = new Intent (activity, classToOpen);
        activity.startActivity(intent);
        activity.finish();
    }

    /** Vérifie si un String est bien un entier.
     * @param s Le string à vérifier
     * @return true si le String est un entier, false sinon
     */

    public static boolean isAnInt(String s){
        try{
            Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    /**Vérifie si un entier est bien compris entre 2 bornes
     * @param choice l'entier à vérifier
     * @param X la borne inférieure (inclue)
     * @param Y la borne supérieure (inclue)
     * @return true si l'entier est bien entre les bornes, false sinon.
     */

    public static boolean isBetweenXAndY(int choice, int X, int Y){
        return (choice >= X) && (choice <= Y);
    }

    public static boolean stringContainsString(final String s1,
                                               final String s2)
    {
        final String s1Comparable = StringUtils.stripAccents(s1).toLowerCase();
        final String s2Comparable = StringUtils.stripAccents(s2).toLowerCase();

        return s1Comparable.contains(s2Comparable);
    }



    public static void showMessage(final String titre,
                                   final String message,
                                   final Context context)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();
    }

    public static void reloadView(Activity activity, Class<?> classToReload){
        Intent i = new Intent(activity, classToReload);
        activity.finish();
        activity.overridePendingTransition(0, 0);
        activity.startActivity(i);
        activity.overridePendingTransition(0, 0);

    }
}