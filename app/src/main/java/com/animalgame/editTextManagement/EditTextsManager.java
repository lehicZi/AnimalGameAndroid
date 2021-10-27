package com.animalgame.editTextManagement;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;

public class EditTextsManager
{
	private final Collection<EditText> listeEditTexts;

	public EditTextsManager()
	{
		this.listeEditTexts = new ArrayList<>();
	}

	void addEditTextInList(final EditText editText)
	{
		this.listeEditTexts.add(editText);
	}

	public Collection<EditText> getListeEditTexts()
	{
		return this.listeEditTexts;
	}

	public int nbEditTexts()
	{
		return this.listeEditTexts.size();
	}


}
