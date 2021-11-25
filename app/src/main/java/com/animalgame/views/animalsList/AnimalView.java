package com.animalgame.views.animalsList;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.animalgame.DataBase;
import com.animalgame.DataShared;
import com.animalgame.R;
import com.animalgame.Utils;
import com.animalgame.objects.Animal;

public class AnimalView extends AppCompatActivity {

    private Animal selectedAnimal = AnimalsListSingleton.getInstance().getSelectedAnimal();

    private SwitchCompat isItUsed;
    private ImageView animalVisual;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.animal_view);
        this.setTitle(selectedAnimal.toString());

        instanciateView();

        setListeners();
    }

    private void instanciateView(){

        isItUsed = findViewById(R.id.isItUsedSwitch);
        animalVisual= findViewById(R.id.animalVisualForView);

        animalVisual.setImageURI(this.selectedAnimal.getVisual());

        setDefaultSwitch();

    }

    @Override
    public void onBackPressed(){
        Utils.openOtherActivity(AnimalsListView.class, this);
    }

    private void setDefaultSwitch(){

        if (selectedAnimal.isUsed()){
            isItUsed.setChecked(true);
            isItUsed.setText("Animal utilisé ");
        }

        else {
            isItUsed.setChecked(false);
            isItUsed.setText("Animal non utilisé ");
        }

    }

    private void setListeners(){

        isItUsed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    isItUsed.setText("Animal utilisé ");
                    selectedAnimal.setUsed(true);


                }

                else{

                    isItUsed.setText("Animal non utilisé ");
                    selectedAnimal.setUsed(false);

                }
                DataBase.updateUse(AnimalView.this, selectedAnimal);

            }
        });



    }

}
