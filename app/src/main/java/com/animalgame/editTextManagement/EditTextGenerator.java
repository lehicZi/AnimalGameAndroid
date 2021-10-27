package com.animalgame.editTextManagement;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.Collection;

public class EditTextGenerator {


    private final ScrollView scrollView;

        private int topMargin;
        private final int editTextWidth;
        private final int editTextHeight;

        private final Activity context;

        private final EditTextListenerManager listenerManager;

        private final EditTextsManager manager;

        private RelativeLayout relativeLayout;

    public EditTextGenerator(final Activity activity, final ScrollView scrollView)
        {
            this.context = activity;
            this.scrollView = scrollView;
            this.listenerManager = new EditTextListenerManager(this);
            this.manager = new EditTextsManager();
            this.topMargin = 5;
            this.editTextWidth = 500;
            this.editTextHeight = 150;
            setRelativeLayout();
        }

    private void setRelativeLayout()
    {
        this.relativeLayout = new RelativeLayout(this.context);
        this.scrollView.addView(this.relativeLayout);
    }


    public EditText addEditText()
    {
        final EditText editText = new EditText(this.context);

        //editText.setMaxLines(1);
        editText.requestFocus();
        editText.setHint("Joueur " + (this.manager.nbEditTexts() + 1));
        //editText.setTextColor(Colors.WHITE);

        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.topMargin = this.topMargin;
        layoutParams.leftMargin = 5;
        layoutParams.rightMargin = 5;


        editText.setLayoutParams(layoutParams);
        editText.setWidth(this.editTextWidth);
        editText.setHeight(this.editTextHeight);

        this.listenerManager.setTextChangeListener(editText);
        this.manager.addEditTextInList(editText);
        this.relativeLayout.addView(editText);

        this.topMargin += 170;

        return editText;
    }

    public Collection<EditText> getEditTextListe()
    {
        return this.manager.getListeEditTexts();
    }

    public Context context()
    {
        return this.context;
    }

    public void clearEditTextList()
    {
        this.manager.getListeEditTexts().clear();
    }

    public EditTextsManager getManager() {
        return manager;
    }

    public void clearLayout(){
        this.relativeLayout.removeAllViews();
        this.topMargin = 5;

    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }
}
