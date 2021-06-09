package com.example.nguyenhuynhkieng;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectView();


        // get firebase
        auth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edtEmail.getText().toString();
                final String Password = edtPassword.getText().toString();

                //check empty
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Login.this, "Enter Email address !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(Login.this, "Enter Password !", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Authenticate user
                auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            if(Password.length()<6){
                                edtPassword.setError("Password too short, enter minimum 6 characters!");
                            } else {
                                Toast.makeText(Login.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Intent intent = new Intent(Login.this, Manager.class);
                            startActivity(intent);

                        }

                    }
                });

            }
        });
    }
    void connectView(){
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

}
