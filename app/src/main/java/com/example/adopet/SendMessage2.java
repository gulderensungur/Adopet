package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SendMessage2 extends AppCompatActivity {

    private ImageButton BackToPetProfileforAdopter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message2);

        BackToPetProfileforAdopter2 = (ImageButton) findViewById(R.id.back_button_3);

        BackToPetProfileforAdopter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendMessage2.this, AdopterActivity.class);
                startActivity(intent);
            }
        });
    }
}