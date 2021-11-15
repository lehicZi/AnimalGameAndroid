package com.animalgame.views.Recycler;

import android.net.Uri;

public class RecyclerItems {

    Uri visual;
    String indication;

    public RecyclerItems(Uri visual, String indication) {
        this.visual = visual;
        this.indication = indication;
    }

    public Uri getVisual() {
        return visual;
    }

    public void setVisual(Uri visual) {
        this.visual = visual;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }
}
