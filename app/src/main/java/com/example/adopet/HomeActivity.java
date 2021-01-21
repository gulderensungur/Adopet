package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button LogoutBtn;
    private Button AdopterRole;
    private Button PetOwnerRole;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LogoutBtn = (Button) findViewById(R.id.log_out_btn);
        AdopterRole=(Button) findViewById(R.id.Adopter_role);
        PetOwnerRole=(Button) findViewById(R.id.Pet_owner_role);

        LogoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });



        PetOwnerRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomeActivity.this, PetOwnerActivity.class);
                startActivity(intent);

            }
        });

        AdopterRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(HomeActivity.this, AdopterActivity.class);
                startActivity(intent);

            }
        });





    }

}