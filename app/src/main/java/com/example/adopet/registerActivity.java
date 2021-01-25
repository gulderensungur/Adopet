package com.example.adopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registerActivity extends AppCompatActivity{

    private Button CreateAccountButton;
    private EditText InputName, InputMail, InputPassword;
    private ProgressDialog loadingBar;
    FirebaseAuth mFAuth;
    Context context = this;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputMail = (EditText) findViewById(R.id.register_email_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputName = (EditText) findViewById(R.id.register_username_input);
        mFAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = InputName.getText().toString();
                String mail = InputMail.getText().toString();
                String password = InputPassword.getText().toString();

                if (name.isEmpty()) {
                  InputName.setError("Please enter your name..");
                  InputName.requestFocus();
                }
                else if (mail.isEmpty()) {
                    InputMail.setError("Please enter your mail..");
                    InputMail.requestFocus();
                }
                else if (password.isEmpty()){
                    InputPassword.setError("Please enter your password..");
                    InputPassword.requestFocus();
                }
                else if(!(mail.isEmpty() && password.isEmpty() && name.isEmpty())) {
                    mFAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(registerActivity.this, "Register Error", Toast.LENGTH_SHORT).show();

                            }else{

                                FirebaseUser user = mFAuth.getCurrentUser();
                                String name = InputName.getText().toString();
                                String password = InputPassword.getText().toString();
                                String mail = user.getEmail();
                                String userid = user.getUid();


                                HashMap<Object,String> hashMap = new HashMap<>();
                                hashMap.put("id", userid);
                                hashMap.put("username",name);
                                hashMap.put("password",password);
                                hashMap.put("mail",mail);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                //path the store user data named "Users"
                                DatabaseReference reference = database.getReference("Users");
                                //put data within hashmap in db
                                reference.child(userid).setValue(hashMap);


                                startActivity(new Intent(registerActivity.this, MainActivity.class ));
                            }
                        }
                    });
                }
                else {
                   loadingBar.setTitle("Create Account");
                    loadingBar.setMessage("Please wait, we are checking the credentials.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();
                    Toast.makeText(registerActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                }
             }
        });

    }

     }

