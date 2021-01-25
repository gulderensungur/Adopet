package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PetOwnerActivity extends AppCompatActivity {

    private Button CreateProfile;
    TextView GoToMyPetProfile;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner);

        GoToMyPetProfile= (TextView) findViewById(R.id.go_to);
        CreateProfile = (Button) findViewById(R.id.btn_create_pet_profile);


        CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(PetOwnerActivity.this, AddMyPetActivity.class);
                startActivity(intent);

            }
        });

        GoToMyPetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetOwnerActivity.this, pet_profile_for_petownerActivity.class);
                startActivity(intent);
            }
        });


    }
}