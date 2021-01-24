package com.example.adopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adopet.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private EditText InputMail,InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    FirebaseAuth mFAuth;
    private FirebaseUser firebaseUser;
    Context context = this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        InputMail = (EditText) findViewById(R.id.login_mail_input);
        InputPassword = (EditText) findViewById(R.id.login_pwd_input);
        mFAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        firebaseUser = mFAuth.getCurrentUser(); // authenticated user

        if(firebaseUser != null){ // check user session

            Intent i = new Intent(loginActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }

       /* mAutStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(loginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(loginActivity.this, "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        }; */
      LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = InputMail.getText().toString();
                String password = InputPassword.getText().toString();


                if (mail.isEmpty()) {
                    InputMail.setError("Please enter your mail..");
                    InputMail.requestFocus();
                }
                if (password.isEmpty()){
                    InputPassword.setError("Please enter your password..");
                    InputPassword.requestFocus();
                }
                if(password.length() < 6) {
                    InputPassword.setError("Your password must be 6 character at least..");
                    InputPassword.requestFocus();
                }
                loadingBar.setTitle("Login Account");
                loadingBar.setMessage("Please wait, we are checking the credentials.");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                mFAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this, HomeActivity.class));
                        } else {
                            Toast.makeText(loginActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    });
                }

        });

    }

   /* @Override
    protected void onStart() {
        super.onStart();
        mFAuth.addAuthStateListener(mAutStateListener);
    } */
}
