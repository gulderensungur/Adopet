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
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;

public class loginActivity extends AppCompatActivity {

    EditText InputMail,InputPassword;
    TextView ForgetPassword;
    Button LoginButton;
    ProgressDialog loadingBar;
    FirebaseAuth mFAuth;
    FirebaseUser user;
    Context context = this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ForgetPassword= (TextView)findViewById(R.id.forget_password_link);
        LoginButton = (Button) findViewById(R.id.login_btn);
        InputMail = (EditText) findViewById(R.id.login_mail_input);
        InputPassword = (EditText) findViewById(R.id.login_pwd_input);
        mFAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        user = mFAuth.getCurrentUser(); // authenticated user






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

//                            //String mail = InputMail.getText().toString();
//                            String password = InputPassword.getText().toString();
//                            String mail = user.getEmail();
//                            String userid = user.getUid();
//
//
//                            HashMap<Object,String> hashMap = new HashMap<>();
//                            hashMap.put("id", userid);
//                            hashMap.put("password",password);
//                            hashMap.put("mail",mail);
//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            //path the store user data named "Users"
//                            DatabaseReference reference = database.getReference("Users");
//                            //put data within hashmap in db
//                            reference.child(userid).setValue(hashMap);

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


}

