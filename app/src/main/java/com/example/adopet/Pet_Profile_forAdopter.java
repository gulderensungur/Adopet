package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pet_Profile_forAdopter extends AppCompatActivity {

    private Button BackToAdopterActivity;

    private Button SendMessageToOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile_for_adopter);

        BackToAdopterActivity = (Button) findViewById(R.id.back_button_1);

        SendMessageToOwner = (Button) findViewById(R.id.send_message_toowner);


        BackToAdopterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(Pet_Profile_forAdopter.this, AdopterActivity.class);
                startActivity(intent);

            }
        });

        SendMessageToOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent(Pet_Profile_forAdopter.this, SendMessage.class);
                startActivity(intent);

            }
        });

    }
}