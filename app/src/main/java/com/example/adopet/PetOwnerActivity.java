package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PetOwnerActivity extends AppCompatActivity {

    private Button CreateProfile;
    private TextView GoProfile;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner);


        CreateProfile = (Button) findViewById(R.id.btn_create_pet_profile);
        GoProfile = (TextView) findViewById(R.id.txt_edit_pet_profile);


        CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(PetOwnerActivity.this, EditMyPetActivity.class);
                startActivity(intent);

            }
        });
        GoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(PetOwnerActivity.this, MyPetActivity.class);
                startActivity(intent);

            }
        });


    }
}