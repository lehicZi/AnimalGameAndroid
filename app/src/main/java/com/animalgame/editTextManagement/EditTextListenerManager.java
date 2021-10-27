package com.animalgame.editTextManagement;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import java.util.ArrayList;

public class EditTextListenerManager
{
    private final EditTextGenerator editTextGenerator;

    public EditTextListenerManager(final EditTextGenerator editTextGenerator)
    {
        this.editTextGenerator = editTextGenerator;
    }

    public void setTextChangeListener(final EditText editText)
    {
        editText.addTextChangedListener(new TextWatcher()
        {
            String sequence;
            int tailleSeq;
            int cursorPosition;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                sequence = s.toString();
                tailleSeq = sequence.length();
                cursorPosition = editText.getSelectionStart();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                    final int backSpaceIndex = s.toString().indexOf('\n');

                    if (backSpaceIndex != -1)
                    {
                        final ArrayList<EditText> listEditText = (ArrayList<EditText>) editTextGenerator.getEditTextListe();
                        final int currentEditTextIndex = listEditText.indexOf(editText);

                        if (currentEditTextIndex != listEditText.size() - 1)
                        {
                            //The next edittext already exists, so we don't wanna create a new one
                            listEditText.get(currentEditTextIndex + 1).requestFocus();
                        }

                        editText.setText(sequence);
                    }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }
}
