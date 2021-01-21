package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditMyPetActivity extends AppCompatActivity {

    private Button CreateButton;
    private EditText PetName, PetType, PetOwnerName, PetAge, OtherFeatures;
    private ProgressDialog loadingbar;
    Context context = this;
    ImageButton imagebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_pet);

        imagebutton = (ImageButton) findViewById(R.id.imgbtn);

        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton.setImageResource(R.drawable.pet9);
            }
        });

        CreateButton = (Button) findViewById(R.id.btn_create);
        PetOwnerName = (EditText) findViewById(R.id.txt_petowner_name);
        PetName = (EditText) findViewById(R.id.txt_name);
        PetType = (EditText) findViewById(R.id.txt_type);
        PetAge = (EditText) findViewById(R.id.txt_age);
        OtherFeatures = (EditText) findViewById(R.id.txt_features);
        loadingbar = new ProgressDialog(this);


        CreateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyPetActivity.class);
                startActivity(intent);


            }
        });


        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateAccount();
            }

        });

    }

    private void CreateAccount() {

        String name = PetName.getText().toString();
        String type = PetType.getText().toString();
        String age = PetAge.getText().toString();
        String pet_owner_name  = PetOwnerName.getText().toString();
        String features = OtherFeatures.getText().toString();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, " Please write pet's name ", Toast.LENGTH_SHORT).show();
        } else {
            loadingbar.setTitle("Create");
            loadingbar.setMessage("Please wait");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();


            Intent intent = new Intent(EditMyPetActivity.this, MyPetActivity.class);
            startActivity(intent);


        }
    }
}

