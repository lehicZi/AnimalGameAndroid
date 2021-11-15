package com.animalgame.objects.gameModes;

import com.animalgame.objects.player.Player;

public class AttributeSwitching {

    private Player switcher;
    private int newAttribute;
    private int oldAttribute;
    private boolean isTheEffective = false;


    public AttributeSwitching(Player switcher, int newAttribute, int oldAttribute) {
        this.switcher = switcher;
        this.newAttribute = newAttribute;
        this.oldAttribute = oldAttribute;
    }



    public Player getSwitcher() {
        return switcher;
    }

    public int getNewAttribute() {
        return newAttribute;
    }

    public int getOldAttribute() {
        return oldAttribute;
    }

    public boolean isTheEffective() {
        return isTheEffective;
    }

    public void setTheEffective(boolean theEffective) {
        isTheEffective = theEffective;
    }
}
