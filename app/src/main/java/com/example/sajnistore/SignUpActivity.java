package com.example.sajnistore;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText userName, userEmail,userPassword;
    Button bt_signup;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        userName = findViewById(R.id.editTextName);
        userEmail = (EditText) findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.editTextPassword);
        bt_signup = findViewById(R.id.buttonRegister);



        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = userName.getText().toString().trim();
                String emailid = userEmail.getText().toString().trim();
                String user_password = userPassword.getText().toString().trim();
                mAuth = FirebaseAuth.getInstance();

                Log.i(TAG, "EMAIL: "+emailid);

                if(TextUtils.isEmpty(user_name)){
                    Toast.makeText(SignUpActivity.this, "Username cannot be Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(emailid)){
                    Toast.makeText(SignUpActivity.this, "Email cannot be Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(user_password)){
                    Toast.makeText(SignUpActivity.this, "Password cannot be Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(user_password.length()<6){
                    Toast.makeText(SignUpActivity.this, "Password length must be atleast 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(user_name,user_password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this,"Signed Up Successfully!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                }else{
                                    Toast.makeText(SignUpActivity.this,"Registration Failed! Please try later.",Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "onComplete: "+task.getException());
                                }

                            }
                        });



            }
        });

    }
}